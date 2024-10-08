package com.intellibucket.mock.company.service.producer;

import com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductApproveKafkaPublisher {
    private final KafkaTemplate<String, CompanyOrderApproveResponseAvroModel> kafkaTemplate;

    public void publish(UUID orderId, ApproveAvroStatus status) {

        CompanyOrderApproveResponseAvroModel avroModel = CompanyOrderApproveResponseAvroModel.newBuilder()
                .setOrderId(orderId)
                .setCreatedAt(OffsetDateTime.now(ZONE_ID).toInstant())
                .setFailureMessage(status == ApproveAvroStatus.REJECTED ? "approve Rejected" : "")
                .setStatus(status)
                .build();

        log.info("Received CompanyOrderApproveResponseAvroModel for order id: {}", orderId);
        try {

            kafkaTemplate.send("company-order-approve-response", UUID.randomUUID().toString(), avroModel);

            log.info("CompanyOrderApproveResponseAvroModel sent to Kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending CompanyOrderApproveResponseAvroModel to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
