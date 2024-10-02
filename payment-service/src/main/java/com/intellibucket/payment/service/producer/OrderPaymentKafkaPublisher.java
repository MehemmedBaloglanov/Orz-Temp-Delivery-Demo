package com.intellibucket.payment.service.producer;

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

//@Slf4j
@Component
@RequiredArgsConstructor

public class OrderPaymentKafkaPublisher {
    private final KafkaTemplate<String, PaymentResponseAvroModel> kafkaTemplate;

    public void publish(UUID orderId, PaymentAvroStatus status) {

        PaymentResponseAvroModel paymentResponseAvroModel = PaymentResponseAvroModel.newBuilder()
                .setOrderId(orderId)
                .setCreatedAt(Instant.from(OffsetDateTime.now(ZONE_ID)))
                .setPaymentStatus(status)
                .setPaymentId(UUID.randomUUID())
                .setPrice(BigDecimal.TEN)
                .setFailureMessages(status == PaymentAvroStatus.FAILED? "Payment failed" : "")
                .build();

//        log.info("Received PaymentResponseAvroModel for order id: {}", orderId);
        try {

            kafkaTemplate.send("payment-response", UUID.randomUUID().toString(), paymentResponseAvroModel);

//            log.info("OrderApproveMessagePublisher sent to Kafka for order id: {}", orderId);
        } catch (Exception e) {
//            log.error("Error while sending OrderApproveMessagePublisher to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }
    }
}
