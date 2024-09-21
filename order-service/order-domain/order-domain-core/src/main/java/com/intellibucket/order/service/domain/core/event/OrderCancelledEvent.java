package com.intellibucket.order.service.domain.core.event;

import com.intellibucket.order.service.domain.core.root.OrderRoot;

import java.time.OffsetDateTime;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 9/10/2024
 * Time: 11:14 AM
 */

public class OrderCancelledEvent extends OrderEvent{
    public OrderCancelledEvent(OrderRoot orderRoot, OffsetDateTime createdAt) {
        super(orderRoot, createdAt);
    }
}
