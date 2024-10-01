package com.intellibucket.order.service.domain.shell.mapper;

import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCompletedEvent;
import com.intellibucket.order.service.domain.core.event.OrderPaidEvent;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentCancelEventPayload;
import org.springframework.stereotype.Component;

@Component
public class OrderShellMapper {

    public OrderResponse orderRootToOrderResponse(OrderRoot orderRoot) {
        return null;
    }

    public TrackOrderResponse orderRootToTrackOrderResponse(OrderRoot orderRoot) {
        return null;
    }


    public OrderAddress userAddressToOrderAddress(UserAddress userPrimaryAddress) {
        return null;
    }

    public OrderPaymentCancelEventPayload orderCancelledEventToOrderPaymentCancelEventPayload(OrderCancelledEvent orderCancelEvent) {
        return null;
    }

    public OrderStartDeliveryEventPayload startDeliveryOrderEventToOrderStartDeliveryEventPayload(StartDeliveryOrderEvent startDeliveryOrderEvent) {
        return null;
    }

    public OrderCompanyEventPayload orderCancelledEventToOrderCompanyEventPayload(OrderCancelledEvent orderCancelEvent) {
        return null;
    }

    public OrderCompanyEventPayload orderPaidEventToOrderCompanyEventPayload(OrderPaidEvent orderPaidEvent) {
        return null;
    }

    public OutboxMessage orderCompletedEventToOutboxMessage(OrderCompletedEvent orderCompletedEvent) {
        return null;
    }

    public OrderCompanyEventPayload orderCompletedEventToOrderCompanyEventPayload(OrderCompletedEvent orderCompletedEvent) {
        return null;
    }
}
