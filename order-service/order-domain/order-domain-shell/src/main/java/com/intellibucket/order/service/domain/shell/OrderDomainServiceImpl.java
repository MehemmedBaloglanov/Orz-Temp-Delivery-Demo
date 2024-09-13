package com.intellibucket.order.service.domain.shell;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.event.*;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;

public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(OrderRoot orderRoot, CompanyID companyID) {
        return null;
    }

    @Override
    public PaymentSuccessEvent payOrder(OrderRoot orderRoot) {
        return null;
    }

    @Override
    public PaymentCanceledEvent cancelOrderPayment(OrderRoot orderRoot) {
        return null;
    }

    @Override
    public OrderAssignedEvent assignOrder(OrderRoot orderRoot) {
        return null;
    }

    @Override
    public OrderDeliveredEvent deliverOrder(OrderRoot orderRoot) {
        return null;
    }

    @Override
    public OrderPreparedEvent orderPrepared(OrderRoot orderRoot) {
        return null;
    }

    @Override
    public OrderPreparingEvent prepareOrder(OrderRoot orderRoot) {
        return null;
    }

    @Override
    public OrderCancelEvent cancelOrder(OrderID orderID) {
        return null;
    }

    @Override
    public OrderRoot traceOrder(OrderNumber orderNumber) {
        return null;
    }
}
