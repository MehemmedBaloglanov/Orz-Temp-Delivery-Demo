package com.intellibucket.order.service.primary.message.listener.adapter.delivery;

import com.intellibucket.order.service.domain.core.event.OrderDeliveredEvent;
import com.intellibucket.order.service.domain.shell.port.input.listener.delivery.DeliveryMessageListener;

public class DeliveryMessageListenerImpl implements DeliveryMessageListener {
    @Override
    public OrderDeliveredEvent orderDelivered(OrderDeliveredEvent order) {
        return null;
    }
}
