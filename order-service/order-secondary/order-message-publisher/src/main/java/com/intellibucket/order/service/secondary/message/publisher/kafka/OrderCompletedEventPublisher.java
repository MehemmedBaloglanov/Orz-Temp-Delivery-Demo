package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.company.OrderCompletedRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.config.OrderServiceConfigData;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.completed.OrderCompletedEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderCompletedEventPublisher;
import com.intellibucket.order.service.secondary.message.publisher.helper.OrderKafkaPublisherHelper;
import com.intellibucket.order.service.secondary.message.publisher.mapper.OrderMessagePublisherDataMapper;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
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
        OrderCompletedEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderCompletedEventPayload.class);

        log.info("Received OrderDeliveryCompletedEventPayload for order id: {}", payload.getOrderId());

        try {
            OrderCompletedRequestAvroModel orderCompletedRequestAvroModel = orderMessagePublisherDataMapper.orderCompetedEventToOrderCompletedRequestAvroModel(payload);
            kafkaProducer.send(
                    orderServiceConfigData.getOrderCompletedTopicName(),
                    UUID.randomUUID().toString(),
                    orderCompletedRequestAvroModel,
                    orderKafkaPublisherHelper.getCallback(orderCompletedRequestAvroModel, message, payload.getOrderId(), outboxCallback));

            log.info("OrderDeliveryCompletedEventPayload sent to Kafka for order id: {}", payload.getOrderId());

        } catch (Exception e) {

            log.error("Error while sending OrderDeliveryCompletedEventPayload to kafka with order id: {}, error: {}", payload.getOrderId(), e.getMessage());
        }
    }
}
