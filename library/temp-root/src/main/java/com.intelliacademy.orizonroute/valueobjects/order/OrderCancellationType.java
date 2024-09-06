package com.intelliacademy.orizonroute.valueobjects.order;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum OrderCancellationType {
    CANCELLED_BY_CUSTOMER,
    CANCELLED_BY_ADMIN,

    CANCELLED_BY_SYSTEM,

    CANCELLED_BY_COURIER;
}
