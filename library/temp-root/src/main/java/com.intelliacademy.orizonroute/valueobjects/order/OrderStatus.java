package com.intelliacademy.orizonroute.valueobjects.order;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum OrderStatus {
    DRAFT,
    ON_PROGRESS,
    COMPLETED,
    DELETED,
    FAILED;

    public Boolean isDraft() {
        return this == DRAFT;
    }

    public Boolean isPendingOnConfirmation() {
        return this == ON_PROGRESS;
    }

    public Boolean isCompleted() {
        return this == COMPLETED;
    }

    public Boolean isDeleted(){
        return this == DELETED;
    }

    public Boolean isReturned() {
        return this == FAILED;
    }


}
