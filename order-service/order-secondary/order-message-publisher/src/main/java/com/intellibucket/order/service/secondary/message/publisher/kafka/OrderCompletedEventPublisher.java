package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.OrderCompletedRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCompletedEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderCompletedEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderCompletedEventPublisher;
import com.intellibucket.order.service.secondary.message.publisher.helper.OrderKafkaPublisherHelper;
import com.intellibucket.order.service.secondary.message.publisher.mapper.OrderMessagePublisherDataMapper;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCompletedEventPublisher implements AbstractOrderCompletedEventPublisher {
    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;
    private final KafkaProducer<String, OrderCompletedRequestAvroModel> kafkaProducer;
    private final OrderKafkaPublisherHelper orderKafkaPublisherHelper;
    private final String TOPIC_NAME = "order-completed";

    @Override
    public void publish(OrderCompletedEventOutboxMessage message, BiConsumer<OrderCompletedEventOutboxMessage, OutboxStatus> callback) throws OrderDomainException {
        OrderCompletedEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderCompletedEventPayload.class);
        String sagaId = message.getSagaId().toString();
        OrderCompletedRequestAvroModel orderCompletedRequestAvroModel = orderMessagePublisherDataMapper.orderCompetedEventToOrderCompletedRequestAvroModel(sagaId, payload);
        kafkaProducer.send(TOPIC_NAME, sagaId, orderCompletedRequestAvroModel, orderKafkaPublisherHelper.getCallback(orderCompletedRequestAvroModel, message, payload));
    }
}
