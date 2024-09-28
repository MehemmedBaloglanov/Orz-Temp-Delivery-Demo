package com.intellibucket.order.service.domain.core.service;

import com.intellibucket.order.service.domain.core.event.*;
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

    OrderPaidEvent orderPay(OrderRoot orderRoot) throws OrderDomainException;

    OrderCancelledEvent orderPaymentCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException;

    void orderCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException;

    void confirmOrder(OrderRoot orderRoot) throws OrderDomainException;

    StartDeliveryOrderEvent preparedOrder(OrderRoot orderRoot) throws OrderDomainException;

    OrderCompletedEvent orderComplete(OrderRoot orderRoot) throws OrderDomainException;


}
