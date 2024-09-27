package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderStartDeliveryEventOutboxMessage;

public interface OrderStartDeliveryOutboxRepository extends BaseOutboxRepository<OrderStartDeliveryEventOutboxMessage> {
}
