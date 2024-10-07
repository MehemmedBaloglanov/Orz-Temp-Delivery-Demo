package com.intellibucket.company.service.message.listener.mapper;

import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponse;
import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponseProduct;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveRequestAvroModel;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Component
public class CompanyMessagePublisherDataMapper {

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
}
