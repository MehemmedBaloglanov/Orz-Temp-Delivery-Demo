package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCancelPaymentEventOutboxMessage;

public interface OrderCancelPaymentOutboxRepository extends BaseOutboxRepository<OrderCancelPaymentEventOutboxMessage> {
}
