package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.PaymentRequestAvroModel;
import com.intellibucket.message.publisher.BaseMessagePublisher;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.config.OrderServiceConfigData;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentCancelEventPayload;
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
public class OrderPaymentCancelMessagePublisher implements BaseMessagePublisher<OutboxMessage> {

    private final KafkaMessageHelper kafkaMessageHelper;
    private final OrderKafkaPublisherHelper orderKafkaPublisherHelper;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;

    @Override
    public void publish(OutboxMessage message, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) throws OrderDomainException {
        OrderPaymentCancelEventPayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderPaymentCancelEventPayload.class);
        String sagaId = message.getSagaId().toString();
        log.info("Received PaymentRequestMessagePublisher for order id: {} and saga id: {}", payload.getOrderId(), sagaId);
        try {
            PaymentRequestAvroModel paymentRequestAvroModel = orderMessagePublisherDataMapper.orderPaymentEventToPaymentCancelAvroModel(sagaId, payload);

            kafkaProducer.send(
                    orderServiceConfigData.getPaymentRequestTopicName(),
                    sagaId,
                    paymentRequestAvroModel,
                    orderKafkaPublisherHelper.getCallback(paymentRequestAvroModel, message, payload.getOrderId(), outboxCallback));

            log.info("PaymentRequestMessagePublisher sent to Kafka for order id: {} and saga id: {}", payload.getOrderId(), sagaId);
        } catch (Exception e) {
            log.error("Error while sending PaymentRequestMessagePublisher to kafka with order id: {} and saga id: {}, error: {}", payload.getOrderId(), sagaId, e.getMessage());
        }


    }
}
