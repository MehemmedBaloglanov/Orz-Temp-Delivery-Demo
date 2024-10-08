package com.intellibucket.mock.payment.service.producer;

import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundAvroStatus;
import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundResponseAvroModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRefundKafkaPublisher {

    private final KafkaTemplate<String, PaymentRefundResponseAvroModel> kafkaTemplate;

    public void publish(UUID orderId, PaymentRefundAvroStatus status) {
        PaymentRefundResponseAvroModel paymentResponseAvroModel = PaymentRefundResponseAvroModel.newBuilder()
                .setOrderId(orderId)
                .setCreatedAt(Instant.from(OffsetDateTime.now(ZONE_ID)))
                .setFailureMessage(status == PaymentRefundAvroStatus.FAILED ? "Payment failed" : "")
                .setStatus(status)
                .build();

        log.info("Received PaymentRefundResponseAvroModel for order id: {}", orderId);
        try {

            kafkaTemplate.send("payment-refund-response", UUID.randomUUID().toString(), paymentResponseAvroModel);

            log.info("PaymentRefundResponseAvroModel sent to Kafka for order id: {}", orderId);
        } catch (Exception e) {
            log.error("Error while sending PaymentRefundResponseAvroModel to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
