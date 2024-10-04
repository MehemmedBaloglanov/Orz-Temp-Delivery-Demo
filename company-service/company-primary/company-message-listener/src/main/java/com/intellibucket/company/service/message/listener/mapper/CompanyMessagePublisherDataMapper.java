package com.intellibucket.company.service.message.listener.mapper;

import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponse;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveRequestAvroModel;
import org.springframework.stereotype.Component;

@Component
public class CompanyMessagePublisherDataMapper {
    public ProductApproveResponse productStockUpdateRequestAvroModelToOrderProductsResponse(CompanyOrderApproveRequestAvroModel message) {
        return ProductApproveResponse.builder()

                .build();
    }
}
