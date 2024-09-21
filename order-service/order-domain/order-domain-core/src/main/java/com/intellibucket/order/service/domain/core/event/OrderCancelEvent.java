package com.intellibucket.order.service.domain.core.event;


import com.intellibucket.order.service.domain.core.root.OrderRoot;

import java.time.OffsetDateTime;

public class OrderCancelEvent extends OrderEvent {
    public OrderCancelEvent(OrderRoot orderRoot, OffsetDateTime createdAt) {
        super(orderRoot, createdAt);
    }
}
