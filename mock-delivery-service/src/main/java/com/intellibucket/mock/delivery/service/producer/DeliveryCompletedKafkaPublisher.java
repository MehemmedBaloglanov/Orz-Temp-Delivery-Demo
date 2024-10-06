package com.intellibucket.mock.delivery.service.producer;

import com.intellibucket.kafka.order.avro.model.delivery.DeliveryAvroStatus;
import com.intellibucket.kafka.order.avro.model.delivery.DeliveryResponseAvroModel;
import com.intellibucket.kafka.order.avro.model.payment.PaymentAvroStatus;
import com.intellibucket.kafka.order.avro.model.payment.PaymentResponseAvroModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor

public class DeliveryCompletedKafkaPublisher {
    private final KafkaTemplate<String, DeliveryResponseAvroModel> kafkaTemplate;

    public void publish(UUID orderId, DeliveryAvroStatus status) {

        DeliveryResponseAvroModel avroModel = DeliveryResponseAvroModel.newBuilder()
                .setOrderId(orderId)
                .setCreatedAt(Instant.from(OffsetDateTime.now(ZONE_ID)))
                .setDeliveryStatus(status)
                .setFailureMessage(status == DeliveryAvroStatus.FAILED ? "delivery failed" : "")
                .build();

        log.info("Received PaymentResponseAvroModel for order id: {}", orderId);
        try {

            kafkaTemplate.send("order-delivered-response", UUID.randomUUID().toString(), avroModel);

            log.info("PaymentResponseAvroModel sent to Kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending PaymentResponseAvroModel to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
