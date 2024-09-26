package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.PaymentRequestAvroModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
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
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaTemplate; //FIXME add kafka template generic types
    private final OrderMessagePublisherDataMapper orderMessagePublisherDataMapper;

    @Override
    public void publish(OrderPaymentOutboxMessage message, BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> callback) throws OrderDomainException {
        OrderPaymentEventPayload orderPaymentEventPayload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), OrderPaymentEventPayload.class);

        String sagaId = message.getSagaId().toString();

        PaymentRequestAvroModel paymentRequestAvroModel = orderMessagePublisherDataMapper.orderPaymentEventToPaymentRequestAvroModel(sagaId, orderPaymentEventPayload);

        orderKafkaPublisherHelper.getCallback(
                paymentRequestAvroModel,
                message,
                orderPaymentEventPayload);

        kafkaTemplate.send(
                "myTopic",
                sagaId,
                paymentRequestAvroModel,
                kafkaMessageHelper.getOrderEventPayload(
                        paymentRequestAvroModel,
                        ));


    }
}
