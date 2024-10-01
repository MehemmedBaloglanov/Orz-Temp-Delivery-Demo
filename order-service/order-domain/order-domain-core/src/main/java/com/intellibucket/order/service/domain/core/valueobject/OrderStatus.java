package com.intellibucket.order.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum OrderStatus {
    CREATED, APPROVED, CANCELLING, CANCELLED, PAID, CONFIRMED, PREPARED, DELIVERING, COMPLETED;

    public Boolean isCreated() {
        return this == CREATED;
    }

    public Boolean isApproved() {
        return this == APPROVED;
    }

    public Boolean isCancelling() {
        return this == CANCELLING;
    }

    public Boolean isCancelled() {
        return this == CANCELLED;
    }

    public Boolean isPaid() {
        return this == PAID;
    }

    public Boolean isConfirmed() {
        return this == CONFIRMED;
    }

    public Boolean isCompleted() {
        return this == COMPLETED;
    }

    public Boolean isPrepared() {
        return this == PREPARED;
    }

    public Boolean isDelivering() {
        return this == DELIVERING;
    }

}
