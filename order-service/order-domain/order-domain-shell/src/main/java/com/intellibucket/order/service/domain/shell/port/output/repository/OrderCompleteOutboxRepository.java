package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCompletedEventOutboxMessage;

public interface OrderCompleteOutboxRepository extends BaseOutboxRepository<OrderCompletedEventOutboxMessage> {
}
