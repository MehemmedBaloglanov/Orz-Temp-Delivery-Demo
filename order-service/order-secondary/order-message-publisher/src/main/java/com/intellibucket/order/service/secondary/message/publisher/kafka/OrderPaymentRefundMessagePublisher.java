package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.payment.PaymentRefundRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.config.OrderServiceConfigData;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentRefundEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderPaymentRefundEventPublisher;
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
public class OrderPaymentRefundMessagePublisher implements AbstractOrderPaymentRefundEventPublisher {

    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderKafkaPublisherHelper orderKafkaPublisherHelper;
    private final KafkaProducer<String, PaymentRefundRequestAvroModel> kafkaProducer;
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;

    @Override
    public void publish(OutboxMessage message, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) throws OrderDomainException {
        OrderPaymentRefundEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderPaymentRefundEventPayload.class);

        log.info("Received OrderPaymentRefundEventPayload for order id: {}", payload.getOrderId());
        try {
            PaymentRefundRequestAvroModel avroModel = orderMessagePublisherDataMapper.orderPaymentRefundEventPayloadToPaymentRefundRequestAvroModel(payload);

            kafkaProducer.send(
                    orderServiceConfigData.getPaymentRefundTopicName(),
                    UUID.randomUUID().toString(),
                    avroModel,
                    orderKafkaPublisherHelper.getCallback(avroModel, message, payload.getOrderId(), outboxCallback));

            log.info("OrderPaymentRefundEventPayload sent to Kafka for order id: {}", payload.getOrderId());
        } catch (Exception e) {
            log.error("Error while sending OrderPaymentRefundEventPayload to kafka with order id: {}, error: {}", payload.getOrderId(), e.getMessage());
        }


    }
}
