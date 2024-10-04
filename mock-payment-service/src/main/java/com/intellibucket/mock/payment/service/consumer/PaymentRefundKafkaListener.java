package com.intellibucket.mock.payment.service.consumer;

import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundAvroStatus;
import com.intellibucket.mock.payment.service.producer.PaymentRefundKafkaPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentRefundKafkaListener implements KafkaConsumer<PaymentRefundRequestAvroModel> {

    private final PaymentRefundKafkaPublisher paymentRefundKafkaPublisher;

    @Override
    @KafkaListener(groupId = "payment-service-group", topics = "payment-refund-request")
    public void receive(List<PaymentRefundRequestAvroModel> messages, List<String> keys, List<Integer> partitions, List<Long> offsets) {
        log.info("{} number of PaymentRefundRequestAvroModel received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());
        messages.forEach(message -> {

            paymentRefundKafkaPublisher.publish(message.getOrderId(), PaymentRefundAvroStatus.COMPLETED);
        });
    }
}
