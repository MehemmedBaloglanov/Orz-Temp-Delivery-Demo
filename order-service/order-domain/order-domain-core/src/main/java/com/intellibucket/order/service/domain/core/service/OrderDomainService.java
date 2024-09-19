package com.intellibucket.order.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;
import com.intellibucket.order.service.domain.core.event.OrderDeliveryEvent;
import com.intellibucket.order.service.domain.core.event.OrderPaidEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 9/10/2024
 * Time: 10:27 AM
 */

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(OrderRoot orderRoot) throws OrderDomainException;

    OrderPaidEvent payOrder(OrderRoot orderRoot) throws OrderDomainException;

    void orderAssign(OrderRoot orderRoot) throws OrderDomainException;

    OrderDeliveryEvent orderDelivered(OrderRoot orderRoot) throws OrderDomainException;

    OrderCancelledEvent orderPaymentCancel(OrderRoot orderRoot) throws OrderDomainException;

    void prepareOrder(OrderRoot orderRoot) throws OrderDomainException;

    void orderCancel(OrderRoot orderRoot) throws OrderDomainException;

    OrderCancelledEvent orderUnAssign(OrderRoot orderRoot) throws OrderDomainException;
}
