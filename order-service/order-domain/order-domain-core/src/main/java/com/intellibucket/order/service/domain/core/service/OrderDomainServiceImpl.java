package com.intellibucket.order.service.domain.core.service;

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
    public OrderPaidEvent orderPay(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.pay();
        return new OrderPaidEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public OrderCancelledEvent orderPaymentCancel(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.initCancel();
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public void orderCancel(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.cancel();
    }

    @Override
    public void approveOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.approve();
    }

    @Override
    public OrderCancelledEvent initCancel(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.initCancel();
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }


    @Override
    public StartDeliveryOrderEvent prepareOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.prepared();
        return new StartDeliveryOrderEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public void orderComplete(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.startDelivery();
    }
}
