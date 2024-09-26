package com.intellibucket.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
