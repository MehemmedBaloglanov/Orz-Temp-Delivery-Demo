package com.intellibucket.company.service.message.listener.mapper;

import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponse;
import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponseProduct;
import com.intellibucket.company.service.domain.shell.dto.message.order.complete.OrderCompletedResponse;
import com.intellibucket.company.service.domain.shell.dto.message.order.complete.OrderCompletedResponseProduct;
import com.intellibucket.company.service.domain.shell.dto.message.order.refund.OrderCompanyRefundResponse;
import com.intellibucket.company.service.domain.shell.dto.message.order.refund.OrderRefundResponseProduct;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderRefundRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.company.OrderCompletedRequestAvroModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMessageListenerDataMapper {

    public ProductApproveResponse productStockUpdateRequestAvroModelToOrderProductsResponse(CompanyOrderApproveRequestAvroModel message) {
        return ProductApproveResponse.builder()
                .orderId(message.getOrderId())
                .products(message.getProducts()
                        .stream()
                        .map(companyApprovalAvroProduct ->
                                ProductApproveResponseProduct.builder()
                                        .productId(companyApprovalAvroProduct.getProductId())
                                        .companyId(companyApprovalAvroProduct.getCompanyId())
                                        .quantity(companyApprovalAvroProduct.getQuantity())
                                        .build()
                        )
                        .collect(Collectors.toList()))
                .createdAt(OffsetDateTime.now())
                .build();
    }

    //todo burda commentlediklerim niye islemir?
    public OrderCompanyRefundResponse companyOrderRefundRequestAvroModelToOrderRefundResponse(CompanyOrderRefundRequestAvroModel message) {
        List<OrderRefundResponseProduct> products = message.getProducts().stream()
                .map(companyRefundAvroProduct -> OrderRefundResponseProduct.builder()
                        .productId(companyRefundAvroProduct.getProductId())
                        .companyId(companyRefundAvroProduct.getCompanyId())
//                        .price(BigDecimal.valueOf(companyRefundAvroProduct.get))//todo
//                        .subTotal(companyRefundAvroProduct.getSubTotal())
                        .build())
                .toList();

        return OrderCompanyRefundResponse.builder()
                .orderId(message.getOrderId())
                .customerId(message.getCustomerId())
                .products(products)
//                .price(message.getPrice())
//                .createdAt(message.getCreatedAt())
                .build();
    }

    public OrderCompletedResponse orderCompletedRequestAvroModelToOrderCompletedResponse(OrderCompletedRequestAvroModel message) {
        List<OrderCompletedResponseProduct> products = message.getProducts().stream()
                .map(completedProduct -> OrderCompletedResponseProduct.builder()
                        .productId(completedProduct.getProductId())
                        .companyId(completedProduct.getCompanyId())
                        .price(completedProduct.getPrice())
                        .subTotal(completedProduct.getSubTotal())
                        .build())
                .toList();

        return OrderCompletedResponse.builder()
                .customerId(message.getCustomerId())
                .orderId(message.getOrderId())
                .products(products)
//                .createdAt(message.getCreatedAt())
                .build();
    }

}

