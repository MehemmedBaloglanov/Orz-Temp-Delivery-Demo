package com.intellibucket.mock.payment.service.controller;

import com.intellibucket.kafka.order.avro.model.payment.PaymentAvroStatus;
import com.intellibucket.mock.payment.service.producer.OrderPaymentKafkaPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final OrderPaymentKafkaPublisher orderPaymentKafkaPublisher;


    @PostMapping("/pay/{orderId}")
    public ResponseEntity<String> pay(@PathVariable("orderId") UUID orderId) {
        orderPaymentKafkaPublisher.publish(orderId, PaymentAvroStatus.COMPLETED);
        return ResponseEntity.ok("Payment processed successfully");
    }

    @PostMapping("/reject/{orderId}")
    public ResponseEntity<String> reject(@PathVariable("orderId") UUID orderId) {
        orderPaymentKafkaPublisher.publish(orderId, PaymentAvroStatus.FAILED);
        // Implementation to reject payment
        return ResponseEntity.ok("Payment rejected successfully");
    }
}
