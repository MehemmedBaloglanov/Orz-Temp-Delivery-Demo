package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.delivery.OrderStartDeliveryRequestAvroModel;
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

import java.util.UUID;
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

        log.info("Received OrderStartDeliveryEventPayload for order id: {}", payload.getOrderId());

        try {
            OrderStartDeliveryRequestAvroModel avroModel = orderMessagePublisherDataMapper.orderPaymentEventToOrderStartDeliveryRequestAvroModel(payload);

            kafkaProducer.send(
                    orderServiceConfigData.getStartDeliveryRequestTopicName(),
                    UUID.randomUUID().toString(),
                    avroModel,
                    orderKafkaPublisherHelper.getCallback(avroModel, message, payload.getOrderId(), outboxCallback));
            log.info("OrderStartDeliveryEventPayload sent to Kafka for order id: {}", payload.getOrderId());

        } catch (Exception e) {
            log.error("Error while sending OrderStartDeliveryEventPayload to kafka with order id: {}, error: {}", payload.getOrderId(), e.getMessage());
        }


    }
}
