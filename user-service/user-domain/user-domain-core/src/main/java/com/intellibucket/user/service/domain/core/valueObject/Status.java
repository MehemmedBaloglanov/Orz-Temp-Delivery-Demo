package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum Status {
    ACTIVE,
    UPDATE,
    DELETED;

    public boolean isCreated() {
        return this == ACTIVE;
    }

    public boolean isUpdate() {
        return this == UPDATE;
    }

    public boolean isDeleted() {
        return this == DELETED;
    }
}
