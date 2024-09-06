package com.intelliacademy.orizonroute.valueobjects.user;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum RoleName {
    ROLE_SUPER_ADMIN,
    ROLE_ADMIN,
    ROLE_CUSTOMER,
    ROLE_COURIER,
    ROLE_COMPANY,
    ROLE_ROBOT,
    ROLE_READ_ONLY_ROBOT,
    ANONYMOUS;
}
