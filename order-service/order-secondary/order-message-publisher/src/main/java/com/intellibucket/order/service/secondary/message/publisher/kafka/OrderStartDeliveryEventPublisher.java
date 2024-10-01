package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.OrderStartDeliveryRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.config.OrderServiceConfigData;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderStartDeliveryEventPublisher;
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
public class OrderStartDeliveryEventPublisher implements AbstractOrderStartDeliveryEventPublisher {


    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderKafkaPublisherHelper orderKafkaPublisherHelper;
    private final KafkaProducer<String, OrderStartDeliveryRequestAvroModel> kafkaProducer;
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;

    @Override
    public void publish(OutboxMessage message, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) throws OrderDomainException {
        OrderStartDeliveryEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderStartDeliveryEventPayload.class);

        String sagaId = message.getSagaId().toString();
        log.info("Received OrderStartDeliveryEventOutboxMessage for order id: {} and saga id: {}", payload.getOrderId(), sagaId);

        try {
            OrderStartDeliveryRequestAvroModel avroModel = orderMessagePublisherDataMapper.orderPaymentEventToOrderStartDeliveryRequestAvroModel(sagaId, payload);

            kafkaProducer.send(
                    orderServiceConfigData.getStartDeliveryRequestTopicName(),
                    sagaId,
                    avroModel,
                    orderKafkaPublisherHelper.getCallback(avroModel, message, payload.getOrderId(), outboxCallback));
            log.info("OrderStartDeliveryEventOutboxMessage sent to Kafka for order id: {} and saga id: {}", payload.getOrderId(), sagaId);

        } catch (Exception e) {
            log.error("Error while sending OrderStartDeliveryEventOutboxMessage to kafka with order id: {} and saga id: {}, error: {}", payload.getOrderId(), sagaId, e.getMessage());
        }


    }
}
