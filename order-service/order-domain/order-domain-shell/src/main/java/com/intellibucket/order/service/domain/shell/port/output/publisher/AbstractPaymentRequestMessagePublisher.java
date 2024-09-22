package com.intellibucket.order.service.domain.shell.port.output.publisher;

import com.food.ordering.system.outbox.OutboxStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.payment.OrderPaymentOutboxMessage;

import java.util.function.BiConsumer;

public interface AbstractPaymentRequestMessagePublisher {
    void publish(OrderPaymentOutboxMessage orderPaymentOutboxMessage,
                 BiConsumer<OrderPaymentOutboxMessage, OutboxStatus> outboxCallback);
}
