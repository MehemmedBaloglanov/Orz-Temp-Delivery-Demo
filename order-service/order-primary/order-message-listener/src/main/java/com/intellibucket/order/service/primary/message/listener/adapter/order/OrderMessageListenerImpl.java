package com.intellibucket.order.service.primary.message.listener.adapter.order;

import com.intellibucket.order.service.domain.core.event.OrderAssignedEvent;
import com.intellibucket.order.service.domain.core.event.OrderCancelEvent;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;
import com.intellibucket.order.service.domain.shell.port.input.listener.order.OrderMessageListener;

public class OrderMessageListenerImpl implements OrderMessageListener {

    @Override
    public <T> OrderCreatedEvent orderCreated(T orderCreatedEvent) {
        return null;
    }

    @Override
    public <T> OrderAssignedEvent orderAssign(T orderAssignedEvent) {
        return null;
    }

    @Override
    public <T> OrderCancelEvent orderCancel(T orderCancelEvent) {
        return null;
    }
}
