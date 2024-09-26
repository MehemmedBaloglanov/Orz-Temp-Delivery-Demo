package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.PaymentRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.config.OrderServiceConfigData;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractPaymentRequestMessagePublisher;
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
public class PaymentRequestMessagePublisher implements AbstractPaymentRequestMessagePublisher {

    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderKafkaPublisherHelper orderKafkaPublisherHelper;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;

    @Override
    public void publish(OrderPaymentOutboxMessage message, BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback) throws OrderDomainException {
        OrderPaymentEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderPaymentEventPayload.class);
        String sagaId = message.getSagaId().toString();
        log.info("Received PaymentRequestMessagePublisher for order id: {} and saga id: {}", payload.getOrderId(), sagaId);
        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagePublisherDataMapper.orderPaymentEventToPaymentRequestAvroModel(sagaId, payload);

            kafkaProducer.send(
                    orderServiceConfigData.getPaymentRequestTopicName(),
                    sagaId,
                    paymentRequestAvroModel,
                    orderKafkaPublisherHelper.getCallback(paymentRequestAvroModel, message, payload, outboxCallback));

            log.info("PaymentRequestMessagePublisher sent to Kafka for order id: {} and saga id: {}", payload.getOrderId(), sagaId);
        } catch (Exception e) {
            log.error("Error while sending PaymentRequestMessagePublisher to kafka with order id: {} and saga id: {}, error: {}", payload.getOrderId(), sagaId, e.getMessage());
        }


    }
}
