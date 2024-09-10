package com.intellibucket.user.service.domain.core.valueObject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum RoleAuthorithy {
    COMPANY,
    CUSTOMER;

    public boolean isRoleCompany() {
        return this == COMPANY;
    }

    public boolean isRoleCustomer() {
        return this == CUSTOMER;
    }
}
