package com.intellibucket.order.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.order.service.domain.core.event.*;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

import static com.intellibucket.constants.DomainConstants.ZONE_ID;

@RequiredArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.validateOrder();
        orderRoot.initializeOrder();
        return new OrderCreatedEvent(orderRoot, OffsetDateTime.now(ZONE_ID));

    }

    @Override
    public OrderCancelEvent initCancel(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.initCancel();
        return new OrderCancelEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public OrderPaidEvent payOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.pay();
        return new OrderPaidEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public void orderAssign(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.assign();
    }

    @Override
    public OrderDeliveryEvent orderDelivered(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.delivery();
        return new OrderDeliveryEvent(orderRoot, OffsetDateTime.now(ZONE_ID));

    }

    @Override
    public OrderCancelledEvent orderPaymentCancel(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.initCancel();
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public void prepareOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.prepared();
    }

    @Override
    public void orderCancel(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.cancel();
    }

    @Override
    public OrderCancelledEvent orderUnAssign(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.initCancel();
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }
}
