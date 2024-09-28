package com.intellibucket.outbox;

import com.intellibucket.exception.DomainException;

public interface OutboxScheduler {
    void processOutboxMessage() throws DomainException;
}
