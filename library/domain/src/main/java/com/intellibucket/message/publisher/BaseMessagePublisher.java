package com.intellibucket.message.publisher;

import com.food.ordering.system.outbox.OutboxStatus;
import com.intellibucket.message.model.BaseMessageModel;

import java.util.function.BiConsumer;

public interface BaseMessagePublisher<T extends BaseMessageModel> {
    void publish(T message, BiConsumer<T, OutboxStatus> callback);
}
