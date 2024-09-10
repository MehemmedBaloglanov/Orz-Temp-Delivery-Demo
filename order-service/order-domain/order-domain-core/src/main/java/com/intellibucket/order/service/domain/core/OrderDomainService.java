package com.intellibucket.order.service.domain.core;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.event.*;
import com.intellibucket.order.service.domain.core.root.OrderRoot;

import java.util.List;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 9/10/2024
 * Time: 10:27 AM
 */

public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(OrderRoot orderRoot, CompanyID companyID);

    OrderPaidEvent  payOrder(OrderRoot orderRoot);

    PaymentCanceledEvent cancelOrderPayment(OrderRoot orderRoot, List<String> failureMessages);

    OrderAssignedEvent assignOrder(OrderRoot orderRoot);

    OrderDeliveredEvent deliverOrder(OrderRoot orderRoot);

    OrderPreparedEvent  orderPrepared(OrderRoot orderRoot);

    OrderPreparingEvent prepareOrder(OrderRoot orderRoot);

    OrderCancelEvent cancelOrder(OrderID orderID);

    OrderRoot traceOrder(OrderNumber orderNumber);


}
