package com.intellibucket.order.service.domain.shell.port.output.publisher;

import com.intellibucket.message.publisher.BaseMessagePublisher;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderStartDeliveryEventOutboxMessage;

public interface AbstractOrderStartDeliveryEventPublisher extends BaseMessagePublisher<OrderStartDeliveryEventOutboxMessage> {
}
