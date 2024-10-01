package com.intellibucket.order.service.secondary.message.publisher.mapper;

import com.intellibucket.kafka.order.avro.model.OrderCompletedRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.OrderStartDeliveryRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.PaymentRequestAvroModel;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderDeliveryCompletedEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentCancelEventPayload;
import org.springframework.stereotype.Component;

@Component
public class OrderMessagePublisherDataMapper {

    public PaymentRequestAvroModel orderPaymentEventToPaymentCancelAvroModel(String sagaId, OrderPaymentCancelEventPayload orderPaymentEventPayload) {
        return null;
    }

    public OrderCompletedRequestAvroModel orderCompetedEventToOrderCompletedRequestAvroModel(String sagaId, OrderDeliveryCompletedEventPayload orderPaymentEventPayload) {

        return null;
    }

    public OrderStartDeliveryRequestAvroModel orderPaymentEventToOrderStartDeliveryRequestAvroModel(String sagaId, OrderStartDeliveryEventPayload payload) {
        return null;
    }
}
