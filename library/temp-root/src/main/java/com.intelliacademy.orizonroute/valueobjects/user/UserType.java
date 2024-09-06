package com.intelliacademy.orizonroute.valueobjects.user;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum UserType {
    COMPANY,
    COURIER,
    CUSTOMER;


    public static UserType fromRole(RoleName roleName){
        return switch (roleName) {
            case ROLE_COMPANY -> COMPANY;
            case ROLE_COURIER -> COURIER;
            case ROLE_CUSTOMER -> CUSTOMER;
            default -> throw new IllegalArgumentException("Unexpected value: " + roleName);
        };
    }

}
