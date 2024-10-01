package com.intellibucket.message.publisher;

import com.intellibucket.exception.DomainException;
import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface BaseMessagePublisher<T extends BaseMessageModel> {
    void publish(T message, BiConsumer<T, OutboxStatus> outboxCallback) throws DomainException;
}
