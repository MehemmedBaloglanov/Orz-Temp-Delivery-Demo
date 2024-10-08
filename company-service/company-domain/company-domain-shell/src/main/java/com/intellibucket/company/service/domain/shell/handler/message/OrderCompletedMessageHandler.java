package com.intellibucket.company.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.shell.dto.message.order.complete.OrderCompletedResponse;
import com.intellibucket.company.service.domain.shell.outbox.helper.CompanyOutboxHelper;
import com.intellibucket.company.service.domain.shell.outbox.model.payload.ProductApprovePayload;
import com.intellibucket.company.service.domain.shell.outbox.model.payload.ProductCompletedPayload;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.intellibucket.company.service.domain.shell.config.CompanyConstants.ORDER_COMPLETED_SAGA_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCompletedMessageHandler {

    private final ProductRepositoryAdapter productRepository;
    private final CompanyOutboxHelper companyOutboxHelper;

    @Transactional
    public void handle(OrderCompletedResponse response) throws CompanyDomainException {
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
                log.error("Insufficient stock for product with id {}", productID);
                failureMessages.add("Insufficient stock for product with id " + productID);
                return;
            }

            productRepository.save(productRoot);
        });

        ProductCompletedPayload productApprovePayload = ProductCompletedPayload.builder()
                .orderId(response.getOrderId())
                .createdAt(response.getCreatedAt().toInstant())
                .build();

//        if (failureMessages.isEmpty()) {
//            productApprovePayload.setStatus(ApproveStatus.APPROVED);
//        } else {
//            productApprovePayload.setStatus(ApproveStatus.REJECTED);todo
//            productApprovePayload.setFailureMessage(String.join(", ", failureMessages));
//        }

        companyOutboxHelper.createAndSaveOutboxMessage(productApprovePayload, ORDER_COMPLETED_SAGA_NAME);
    }
}
