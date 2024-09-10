package com.intellibucket.order.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum OrderStatus {
    CREATED,
    CANCELLING,
    CANCELLED,
    PAID,
    ASSIGNED,
    PREPARING,
    PREPARED,
    DELIVERING,
    COMPLETED;

    public Boolean isCreated() {
        return this == CREATED;
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

    public Boolean isAssigned() {
        return this == ASSIGNED;
    }

    public Boolean isCompleted() {
        return this == COMPLETED;
    }

    public Boolean isPreparing() {
        return this == PREPARING;
    }

    public Boolean isPrepared() {
        return this == PREPARED;
    }

    public Boolean isDelivering() {
        return this == DELIVERING;
    }

}
