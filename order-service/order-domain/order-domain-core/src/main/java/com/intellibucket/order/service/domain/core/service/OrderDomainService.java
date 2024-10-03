package com.intellibucket.order.service.domain.core.service;

import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCompletedEvent;
import com.intellibucket.order.service.domain.core.event.OrderPaidEvent;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 9/10/2024
 * Time: 10:27 AM
 */

public interface OrderDomainService {

    void validateAndInitiateOrder(OrderRoot orderRoot) throws OrderDomainException;

    OrderPaidEvent orderPay(OrderRoot orderRoot) throws OrderDomainException;

    OrderCancelledEvent orderPaymentCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException;

    OrderCancelledEvent orderCustomerCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException;

    OrderCancelledEvent orderCompanyCancel(OrderRoot orderRoot, OrderItemRoot orderItemRoot, String failureMessage) throws OrderDomainException;

    void approveOrder(OrderRoot orderRoot) throws OrderDomainException;

    void orderCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException;

    void confirmOrder(OrderRoot orderRoot) throws OrderDomainException;

    StartDeliveryOrderEvent preparedOrder(OrderRoot orderRoot) throws OrderDomainException;

    OrderCompletedEvent orderComplete(OrderRoot orderRoot) throws OrderDomainException;


}
