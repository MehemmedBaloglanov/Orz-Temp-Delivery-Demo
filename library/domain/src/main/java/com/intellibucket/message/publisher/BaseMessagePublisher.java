package com.intellibucket.message.publisher;

import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface BaseMessagePublisher<T extends BaseMessageModel> {
    void publish(T message, BiConsumer<T, OutboxStatus> outboxCallback) throws OrderDomainException;
}
