package com.intellibucket.order.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.event.*;
import com.intellibucket.order.service.domain.core.root.OrderRoot;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 9/10/2024
 * Time: 10:27 AM
 */

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(OrderRoot orderRoot, CompanyID companyID);

    PaymentSuccessEvent payOrder(OrderRoot orderRoot);

    PaymentCanceledEvent cancelOrderPayment(OrderRoot orderRoot);

    OrderAssignedEvent assignOrder(OrderRoot orderRoot);

    OrderDeliveredEvent deliverOrder(OrderRoot orderRoot);

    OrderPreparedEvent  orderPrepared(OrderRoot orderRoot);

    OrderPreparingEvent preparingOrder(OrderRoot orderRoot);

    OrderCancelEvent cancelOrder(OrderID orderID);

    OrderRoot traceOrder(OrderNumber orderNumber);


}
