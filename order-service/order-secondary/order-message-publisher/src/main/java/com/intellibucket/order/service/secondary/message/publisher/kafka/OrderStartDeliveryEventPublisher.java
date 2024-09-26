package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderStartDeliveryEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderStartDeliveryEventPublisher;
import com.intellibucket.order.service.secondary.message.publisher.mapper.OrderMessagePublisherDataMapper;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStartDeliveryEventPublisher implements AbstractOrderStartDeliveryEventPublisher {

    private final KafkaTemplate<String, T> kafkaProducer;
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;

    @Override
    public void publish(OrderStartDeliveryEventOutboxMessage message, BiConsumer<OrderStartDeliveryEventOutboxMessage, OutboxStatus> callback) {

    }
}
