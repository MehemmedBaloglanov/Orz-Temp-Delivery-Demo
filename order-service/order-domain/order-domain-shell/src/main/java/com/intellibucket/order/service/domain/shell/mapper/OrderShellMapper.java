package com.intellibucket.order.service.domain.shell.mapper;

import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderCancelPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderStartDeliveryEventPayload;
import org.springframework.stereotype.Component;

@Component
public class OrderShellMapper {

    public OrderResponse orderRootToOrderResponse(OrderRoot orderRoot) {
        return null;
    }

    public TrackOrderResponse orderRootToTrackOrderResponse(OrderRoot orderRoot) {
        return null;
    }

    public OrderPaymentEventPayload orderCreatedEventToOrderPaymentEventPayload(OrderCreatedEvent orderCreatedEvent) {
        return null;
    }

    public OrderAddress userAddressToOrderAddress(UserAddress userPrimaryAddress) {
        return null;
    }

    public OrderCancelPaymentEventPayload orderCancelledEventToOrderPaymentCancelEventPayload(OrderCancelledEvent orderCancelEvent) {
        return null;
    }

    public OrderStartDeliveryEventPayload startDeliveryOrderEventToOrderStartDeliveryEventPayload(StartDeliveryOrderEvent startDeliveryOrderEvent) {
        return null;
    }
}
