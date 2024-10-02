package com.intellibucket.domain.message.publisher;

import com.intellibucket.domain.exception.DomainException;
import com.intellibucket.domain.message.model.BaseMessageModel;
import com.intellibucket.outbox.OutboxStatus;

import java.util.function.BiConsumer;

public interface BaseMessagePublisher<T extends BaseMessageModel> {
    void publish(T message, BiConsumer<T, OutboxStatus> outboxCallback) throws DomainException;
}
