package com.intellibucket.order.service.domain.core.valueobject;

public enum OrderItemStatus {
    DRAFT, CONFIRMED, REJECTED, PREPARED;

    public Boolean isDraft() {
        return this == DRAFT;
    }

    public Boolean isConfirmed() {
        return this == CONFIRMED;
    }

    public Boolean isRejected() {
        return this == REJECTED;
    }

    public Boolean isPrepared() {
        return this == PREPARED;
    }
}
