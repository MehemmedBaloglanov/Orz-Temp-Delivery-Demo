package com.intellibucket.order.service.repository.model.outbox;

import com.intellibucket.outbox.OutboxStatus;

public enum OutboxJpaStatus {
    STARTED, COMPLETED, FAILED
}
