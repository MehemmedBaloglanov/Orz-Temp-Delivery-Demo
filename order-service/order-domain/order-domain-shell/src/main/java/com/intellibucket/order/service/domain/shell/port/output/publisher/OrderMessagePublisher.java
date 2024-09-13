package com.intellibucket.order.service.domain.shell.port.output.publisher;

import com.intellibucket.order.service.domain.core.event.OrderAssignedEvent;
import com.intellibucket.order.service.domain.core.event.OrderCancelEvent;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;
import com.intellibucket.order.service.domain.core.event.OrderPreparedEvent;

public interface OrderMessagePublisher {

    void createOrder(OrderCreatedEvent orderCreatedEvent);
    void cancelOrder(OrderCancelEvent orderCancelEvent);
    void assignOrder(OrderAssignedEvent orderAssignedEvent);
    void orderPreparedEvent(OrderPreparedEvent orderPreparedEvent);
}

