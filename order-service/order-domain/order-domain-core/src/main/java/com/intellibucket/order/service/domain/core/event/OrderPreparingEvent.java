package com.intellibucket.order.service.domain.core.event;

import com.intellibucket.order.service.domain.core.root.OrderRoot;

import java.time.OffsetDateTime;

public class OrderPreparingEvent extends OrderEvent{

    public OrderPreparingEvent(OrderRoot orderRoot, OffsetDateTime createdAt) {
        super(orderRoot, createdAt);
    }

}
