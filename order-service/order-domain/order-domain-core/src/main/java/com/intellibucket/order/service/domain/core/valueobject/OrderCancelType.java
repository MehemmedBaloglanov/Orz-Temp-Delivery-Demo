package com.intellibucket.order.service.domain.core.valueobject;

public enum OrderCancelType {
    SYSTEM, CUSTOMER, COMPANY;

    public Boolean isSystem() {
        return this == OrderCancelType.SYSTEM;
    }

    public Boolean isCustomer() {
        return this == OrderCancelType.CUSTOMER;
    }

    public Boolean isCompany() {
        return this == OrderCancelType.COMPANY;
    }
}
