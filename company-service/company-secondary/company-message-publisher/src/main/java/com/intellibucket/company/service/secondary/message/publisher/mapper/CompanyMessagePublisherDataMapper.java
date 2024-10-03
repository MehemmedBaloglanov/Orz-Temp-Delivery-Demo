package com.intellibucket.company.service.secondary.message.publisher.mapper;

import com.intellibucket.company.service.domain.shell.outbox.model.payload.ProductApprovePayload;
import com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel;
import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import org.springframework.stereotype.Component;

@Component
public class CompanyMessagePublisherDataMapper {

    public CompanyOrderApproveResponseAvroModel productApprovePayloadToCompanyOrderApproveRequestAvroModel(ProductApprovePayload payload) {
        return CompanyOrderApproveResponseAvroModel.newBuilder()
                .setOrderId(payload.getOrderId())
                .setCreatedAt(payload.getCreatedAt())
                .setFailureMessage(payload.getFailureMessage() == null ? "" : payload.getFailureMessage())
                .setStatus(covertStatus(payload.getStatus()))
                .build();
    }

    private ApproveAvroStatus covertStatus(ApproveStatus status) {
        return switch (status) {
            case APPROVED -> ApproveAvroStatus.APPROVED;
            case REJECTED -> ApproveAvroStatus.REJECTED;
            default -> ApproveAvroStatus.REJECTED;
        };
    }
}
