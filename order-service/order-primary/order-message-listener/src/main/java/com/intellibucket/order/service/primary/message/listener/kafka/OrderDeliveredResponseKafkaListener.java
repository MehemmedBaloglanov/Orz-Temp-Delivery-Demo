package com.intellibucket.order.service.primary.message.listener.kafka;

import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.DeliveryResponseAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractDeliveryMessageListener;
import com.intellibucket.order.service.primary.message.listener.mapper.OrderMessageListenerDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDeliveredResponseKafkaListener implements KafkaConsumer<DeliveryResponseAvroModel> {

    private final OrderMessageListenerDataMapper orderMessageListenerDataMapper;
    private final AbstractDeliveryMessageListener deliveryMessageListener;

    @Override
    @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}", topics = "${order-service.order-delivered-response-topic-name}")
    public void receive(@Payload List<DeliveryResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of delivered responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(deliveryResponseAvroModel -> {
            DeliveryResponse deliveryResponse = orderMessageListenerDataMapper.deliveredResponseAvroModelToDeliveryResponse(deliveryResponseAvroModel);
            try {
                deliveryMessageListener.orderDelivered(deliveryResponse);
            } catch (OrderNotFoundException e) {
                //NO-OP for OrderNotFoundException
                log.error("No order found for order id: {}", deliveryResponseAvroModel.getOrderId());
            } catch (OrderDomainException e) {
                log.error("Ops order unknown exception for order id: {}", deliveryResponse.getOrderId());
            }
        });

    }
}
