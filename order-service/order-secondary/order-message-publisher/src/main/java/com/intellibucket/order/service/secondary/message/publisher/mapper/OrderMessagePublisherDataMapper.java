package com.intellibucket.order.service.secondary.message.publisher.mapper;

import com.intellibucket.kafka.order.avro.model.OrderCompletedRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.PaymentRequestAvroModel;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCompletedEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderCompletedEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderPaymentEventPayload;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagePublisherDataMapper {

    public PaymentRequestAvroModel orderPaymentEventToPaymentRequestAvroModel(String sagaId, OrderPaymentEventPayload orderPaymentEventPayload) {
        return null;
    }

    public OrderCompletedRequestAvroModel orderCompetedEventToOrderCompletedRequestAvroModel(String sagaId, OrderCompletedEventPayload orderPaymentEventPayload) {

        return null;
    }
}
