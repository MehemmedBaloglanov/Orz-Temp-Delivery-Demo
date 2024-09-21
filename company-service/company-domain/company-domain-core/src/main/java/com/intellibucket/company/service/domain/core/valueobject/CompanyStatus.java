package com.intellibucket.company.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum CompanyStatus {
    DRAFT,
    ACTIVE,
    BANNED,
    DISABLED;



    public Boolean isDraft() {
        return this == DRAFT;
    }

    public Boolean isActive() {
        return this == ACTIVE;
    }

    public Boolean isBanned() {
        return this == BANNED;
    }

    public Boolean isDisabled() {
        return this == DISABLED;
    }
}

