package com.intellibucket.company.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponse;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.outbox.helper.CompanyOutboxHelper;
import com.intellibucket.company.service.domain.shell.outbox.model.payload.ProductApprovePayload;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.intellibucket.company.service.domain.shell.config.CompanyConstants.ORDER_APPROVE_SAGA_NAME;
import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApproveMessageHandler {
    private final ProductRepositoryAdapter productRepository;
    private final CompanyOutboxHelper companyOutboxHelper;

    @Transactional
    public void handle(ProductApproveResponse response) throws CompanyDomainException {
        OrderID orderID = OrderID.of(response.getOrderId());
        List<String> failureMessages = new ArrayList<>();
        response.getProducts().forEach(product -> {
                    ProductID productID = ProductID.of(product.getProductId());
                    Optional<ProductRoot> productRootOptional = productRepository.findById(productID);
                    if (productRootOptional.isEmpty()) {
                        log.error("Product with id {} not found", productID);
                        failureMessages.add("Product with id " + productID + " not found");
                        return;
                    }
                    ProductRoot productRoot = productRootOptional.get();

                    try {
                        productRoot.decreaseStockQuantity(productRoot.getStockQuantity());
                    } catch (ValidateException e) {

                        log.error("Product with id {} is too small", productID);
                        failureMessages.add("Product with id " + productID + " is too small");
                        return;
                    }
                    productRepository.save(productRoot);
                }
        );
        ProductApprovePayload productApprovePayload = ProductApprovePayload.builder()
                .orderId(response.getOrderId())
                .createdAt(OffsetDateTime.now(ZONE_ID).toInstant())
                .build();
        if (failureMessages.isEmpty()) {
            productApprovePayload.setStatus(ApproveStatus.APPROVED);
        } else {
            productApprovePayload.setStatus(ApproveStatus.REJECTED);
            productApprovePayload.setFailureMessage(String.join(", ", failureMessages));
        }

        companyOutboxHelper.createAndSaveOutboxMessage(productApprovePayload, ORDER_APPROVE_SAGA_NAME);
    }
}
