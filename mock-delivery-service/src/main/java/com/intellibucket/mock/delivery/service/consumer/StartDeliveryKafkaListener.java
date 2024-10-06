package com.intellibucket.mock.delivery.service.consumer;

import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.delivery.DeliveryAvroStatus;
import com.intellibucket.kafka.order.avro.model.delivery.OrderStartDeliveryRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundRequestAvroModel;
import com.intellibucket.mock.delivery.service.producer.DeliveryCompletedKafkaPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDeliveryKafkaListener implements KafkaConsumer<OrderStartDeliveryRequestAvroModel> {

    private final DeliveryCompletedKafkaPublisher deliveryCompletedKafkaPublisher;

    @Override
    @KafkaListener(groupId = "delivery-service-group", topics = "start-delivery-request")
    public void receive(List<OrderStartDeliveryRequestAvroModel> messages, List<String> keys, List<Integer> partitions, List<Long> offsets) {
        log.info("{} number of PaymentRefundRequestAvroModel received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());
        messages.forEach(message -> {

            deliveryCompletedKafkaPublisher.publish(message.getOrderId(), DeliveryAvroStatus.DELIVERED);
        });
    }
}
