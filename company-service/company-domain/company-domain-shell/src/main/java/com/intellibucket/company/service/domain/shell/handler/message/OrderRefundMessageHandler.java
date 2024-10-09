package com.intellibucket.company.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.shell.dto.message.order.refund.OrderRefundResponseProduct;
import com.intellibucket.company.service.domain.shell.outbox.helper.CompanyOutboxHelper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRefundMessageHandler {

    private final ProductRepositoryAdapter productRepositoryAdapter;
    private final CompanyOutboxHelper companyOutboxHelper;

    @Transactional
    public void handle(OrderRefundResponseProduct refundResponseProduct) {
        List<String> failureMessages = new ArrayList<>();

        ProductID productID = ProductID.of(refundResponseProduct.getProductId());
        Optional<ProductRoot> productRootOptional = productRepositoryAdapter.findById(productID);

        if (productRootOptional.isEmpty()) {
            log.error("Product with id {} not found for refund process", productID);
            failureMessages.add("Product with id " + productID + " not found for refund");
        } else {
            ProductRoot productRoot = productRootOptional.get();

            try {
                productRoot.increaseStockQuantity(refundResponseProduct.getPrice().intValue());
                productRepositoryAdapter.save(productRoot);
                log.info("Product with id {} successfully refunded", productID);
            } catch (Exception e) {
                log.error("Failed to process refund for product with id {}: {}", productID, e.getMessage());
                failureMessages.add("Failed to refund product with id " + productID + ": " + e.getMessage());
            }
        }

    }
}
