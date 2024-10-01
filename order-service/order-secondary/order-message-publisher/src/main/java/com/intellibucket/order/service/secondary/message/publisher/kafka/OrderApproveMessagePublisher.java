package com.intellibucket.order.service.secondary.message.publisher.kafka;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderApproveEventPublisher;
import com.intellibucket.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public class OrderApproveMessagePublisher implements AbstractOrderApproveEventPublisher {
    @Override
    public void publish(OutboxMessage message, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) throws OrderDomainException {

    }
}
