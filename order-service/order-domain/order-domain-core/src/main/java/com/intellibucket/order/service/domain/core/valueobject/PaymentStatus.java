package com.intellibucket.order.service.domain.core.valueobject;

public enum PaymentStatus {
    COMPLETED, CANCELLED, FAILED;

    public Boolean isCompleted() {
        return this == COMPLETED;
    }

    public Boolean isCancelled() {
        return this == CANCELLED;
    }

    public Boolean isFailed() {
        return this == FAILED;
    }
}
