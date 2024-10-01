package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.OrderCompletedRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.config.OrderServiceConfigData;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderDeliveryCompletedEventPayload;
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
    private final OrderServiceConfigData orderServiceConfigData;

    @Override
    public void publish(OutboxMessage message, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) throws OrderDomainException {
        OrderDeliveryCompletedEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderDeliveryCompletedEventPayload.class);
        String sagaId = message.getSagaId().toString();

        log.info("Received OrderCompletedEventPublisher for order id: {} and saga id: {}", payload.getOrderId(), sagaId);

        try {
            OrderCompletedRequestAvroModel orderCompletedRequestAvroModel = orderMessagePublisherDataMapper.orderCompetedEventToOrderCompletedRequestAvroModel(sagaId, payload);
            kafkaProducer.send(
                    orderServiceConfigData.getCompleteOrderRequestTopicName(),
                    sagaId,
                    orderCompletedRequestAvroModel,
                    orderKafkaPublisherHelper.getCallback(orderCompletedRequestAvroModel, message, payload.getOrderId(), outboxCallback));

            log.info("OrderCompletedEventPublisher sent to Kafka for order id: {} and saga id: {}", payload.getOrderId(), sagaId);

        } catch (Exception e) {

            log.error("Error while sending OrderCompletedEventPublisher to kafka with order id: {} and saga id: {}, error: {}", payload.getOrderId(), sagaId, e.getMessage());
        }
    }
}
