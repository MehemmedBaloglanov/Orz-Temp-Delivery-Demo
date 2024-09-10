package com.intellibucket.company.service.domain.core.valueobject;

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

