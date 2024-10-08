package com.intellibucket.order.service.domain.core.service;

import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCompletedEvent;
import com.intellibucket.order.service.domain.core.event.OrderPaidEvent;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

    /**
     * Validates and initiates the given order.
     *
     * @param orderRoot the root object of the order to be validated and initiated.
     * @throws OrderDomainException if the order validation or initialization fails.
     */
    @Override
    public void validateAndInitiateOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.validateOrder();
        orderRoot.initializeOrder();
        log.info("Order with id: {} is initiated", orderRoot.getRootID().value());
        log.debug("Order with id: {}", orderRoot);
    }

    /**
     * Cancels the order payment due to a system failure.
     *
     * @param orderRoot      the root object of the order to be cancelled.
     * @param failureMessage the message describing the reason for the cancellation.
     * @return an OrderCancelledEvent indicating the order has been cancelled.
     * @throws OrderDomainException if the order cancellation fails.
     */
    @Override
    public OrderCancelledEvent orderPaymentCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException {
        orderRoot.cancelBySystem();
        orderRoot.initCancel(failureMessage);
        log.info("Order payment is cancelling for order id: {}", orderRoot.getRootID().value());
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    /**
     * Cancels the order initiated by the customer.
     *
     * @param orderRoot      the root object of the order to be cancelled.
     * @param failureMessage the message describing the reason for the cancellation.
     * @return an OrderCancelledEvent indicating the order has been cancelled.
     * @throws OrderDomainException if the order cancellation fails.
     */
    @Override
    public OrderCancelledEvent orderCustomerCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException {
        orderRoot.cancelByCustomer();
        orderRoot.initCancel(failureMessage);
        log.info("User cancelling for order id: {}", orderRoot.getRootID().value());
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    /**
     * Cancels the order initiated by the company.
     *
     * @param orderRoot      the root object of the order to be cancelled.
     * @param orderItemRoot  the root object of the order item associated with the company.
     * @param failureMessage the message describing the reason for the cancellation.
     * @return an OrderCancelledEvent indicating the order has been cancelled.
     * @throws OrderDomainException if the order cancellation fails.
     */
    @Override
    public OrderCancelledEvent orderCompanyCancel(OrderRoot orderRoot, OrderItemRoot orderItemRoot, String failureMessage) throws OrderDomainException {
        orderRoot.cancelByCompany();
        orderRoot.initCancel(failureMessage);
        log.info("Company is cancelling for order id: {}, companyId: {}", orderRoot.getRootID().value(), orderItemRoot.getCompanyID());
        return new OrderCancelledEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    /**
     * Approves the given order.
     *
     * @param orderRoot the root object of the order to be approved.
     * @throws OrderDomainException if the order approval fails.
     */
    @Override
    public void approveOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.approve();
        log.info("Order with id: {} is approved", orderRoot.getRootID().value());
    }

    /**
     * Cancels the given order with a specified failure message.
     *
     * @param orderRoot      the root object of the order to be cancelled.
     * @param failureMessage the message describing the reason for the cancellation.
     * @throws OrderDomainException if the order cancellation fails.
     */
    @Override
    public void orderCancel(OrderRoot orderRoot, String failureMessage) throws OrderDomainException {
        orderRoot.cancel(failureMessage);
        log.info("Order with id: {} is cancelled", orderRoot.getRootID().value());
    }

    /**
     * Processes the payment for the given order.
     *
     * @param orderRoot the root object of the order to be paid.
     * @return an OrderPaidEvent indicating the order has been paid.
     * @throws OrderDomainException if the order payment fails.
     */
    @Override
    public OrderPaidEvent orderPay(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.pay();
        log.info("Order with id: {} is paid", orderRoot.getRootID().value());
        return new OrderPaidEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }


    /**
     * Prepares the given order for delivery.
     *
     * @param orderRoot the root object of the order to be prepared.
     * @return a StartDeliveryOrderEvent indicating the order is ready for delivery.
     * @throws OrderDomainException if the order preparation fails.
     */
    @Override
    public void preparedOrder(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.prepared();
        log.info("Order with id: {} is prepared", orderRoot.getRootID().value());
    }

    /**
     * Completes the given order.
     *
     * @param orderRoot the root object of the order to be completed.
     * @return an OrderCompletedEvent indicating the order has been completed.
     * @throws OrderDomainException if the order completion fails.
     */
    @Override
    public OrderCompletedEvent orderComplete(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.completed();
        log.info("Order with id: {} is completed", orderRoot.getRootID().value());
        return new OrderCompletedEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public StartDeliveryOrderEvent startDelivery(OrderRoot orderRoot) throws OrderDomainException {
        orderRoot.startDelivery();
        return new StartDeliveryOrderEvent(orderRoot, OffsetDateTime.now(ZONE_ID));
    }
}
