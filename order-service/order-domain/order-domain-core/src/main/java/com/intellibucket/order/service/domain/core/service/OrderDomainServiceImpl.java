package com.intellibucket.order.service.domain.core.service;

import com.intellibucket.order.service.domain.core.event.*;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderCancelType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.List;

import static com.intellibucket.constants.DomainConstants.ZONE_ID;

@Slf4j
@RequiredArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainService {

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.validateOrder();
        orderRoot.initializeOrder();
        log.info("Order with id: {} is initiated", orderRoot.getRootID().value());
        return new OrderCreatedEvent(orderRoot, OffsetDateTime.now(ZONE_ID));

    }

    @Override
    public OrderPaidEvent orderPay(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.pay();
        log.info("Order with id: {} is paid", orderRoot.getRootID().value());
        return new OrderPaidEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public OrderCancelledEvent orderPaymentCancel(OrderRoot orderRoot, OrderCancelType orderCancelType, List<String> failureMessages) throws OrderDomainException {
        orderRoot.initCancel(failureMessages);
        log.info("Order payment is cancelling for order id: {}", orderRoot.getRootID().value());
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public void orderCancel(OrderRoot orderRoot, OrderCancelType orderCancelType, List<String> failureMessages) throws OrderDomainException {
        orderRoot.cancel(failureMessages, orderCancelType);
        log.info("Order with id: {} is cancelled", orderRoot.getRootID().value());
    }

    @Override
    public void approveOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.approve();
        log.info("Order with id: {} is approved", orderRoot.getRootID().value());
    }


    @Override
    public StartDeliveryOrderEvent prepareOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.prepared();
        log.info("Order with id: {} is prepared", orderRoot.getRootID().value());
        return new StartDeliveryOrderEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public OrderCompletedEvent orderComplete(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.startDelivery();
        log.info("Order with id: {} is completed", orderRoot.getRootID().value());
        return new OrderCompletedEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }
}
