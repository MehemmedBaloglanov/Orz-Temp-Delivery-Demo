package com.intellibucket.order.service.domain.shell.port.input.listener.order;

import com.intellibucket.order.service.domain.core.event.OrderAssignedEvent;
import com.intellibucket.order.service.domain.core.event.OrderCancelEvent;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;

public interface OrderMessageListener {
    <T> OrderCreatedEvent orderCreated(T orderCreatedEvent);

    <T> OrderAssignedEvent orderAssign(T orderAssignedEvent);

    <T> OrderCancelEvent orderCancel(T orderCancelEvent);

}
