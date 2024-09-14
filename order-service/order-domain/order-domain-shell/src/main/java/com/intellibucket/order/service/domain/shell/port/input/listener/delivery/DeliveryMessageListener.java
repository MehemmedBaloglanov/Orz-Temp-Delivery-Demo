package com.intellibucket.order.service.domain.shell.port.input.listener.delivery;

import com.intellibucket.order.service.domain.core.event.OrderDeliveredEvent;

public interface DeliveryMessageListener {
    OrderDeliveredEvent orderDelivered(OrderDeliveredEvent order);
}
