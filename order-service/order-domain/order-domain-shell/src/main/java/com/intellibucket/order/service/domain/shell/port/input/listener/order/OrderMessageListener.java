package com.intellibucket.order.service.domain.shell.port.input.listener.order;

import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;

public interface OrderMessageListener {
    OrderCreatedEvent orderCreated(OrderCreatedEvent orderCreatedEvent);
    OrderCancelledEvent orderCancel(OrderCancelledEvent orderCancelledEvent);
}
