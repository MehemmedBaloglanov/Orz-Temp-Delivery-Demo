package com.intellibucket.order.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.core.valueobject.OrderCancelType;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@SuperBuilder
@Getter
public class OrderRoot extends AggregateRoot<OrderID> {


    private final UserID userId;
    private final OrderAddress address;
    private final Money price;
    private final List<OrderItemRoot> items;

    private OrderNumber orderNumber;
    private OrderStatus status;
    private String errorMessage;
    private OrderCancelType cancelType;


    public OrderRoot initializeOrder() {
        orderNumber = OrderNumber.generate();
        status = OrderStatus.CREATED;
        return this;
    }

    public OrderRoot initCancel(String failureMessages) throws OrderDomainException {
        if (cancelType == null) {
            log.error("Order is not valid cancel type with id: {}", this.getRootID());
            throw new OrderDomainException("Order is not valid cancel type with id: " + this.getRootID());
        }
        if (status.isDelivering() || status.isCompleted() || status.isCancelled()) {
            throw new OrderDomainException("Order is not in correct state for the initCancel operation!");
        }

        this.status = OrderStatus.CANCELLING;
        return this;
    }

    public OrderRoot cancel(String failureMessages) throws OrderDomainException {
        if (status.isCancelling() || status.isCreated()) {
            throw new OrderDomainException("Cannot cancel order");
        }
        this.status = OrderStatus.CANCELLED;
        return this;
    }


    public OrderRoot pay() throws OrderDomainException {

        if (!status.isCreated()) {
            throw new OrderDomainException("Cannot pay order");
        }
        this.status = OrderStatus.PAID;

        return this;
    }

    //when stock is ended return initCancel
    public OrderRoot comfirm() throws OrderDomainException {
        if (!status.isPaid()) {
            throw new OrderDomainException("Cannot comfirm order");
        }
        this.status = OrderStatus.CONFIRMED;
        return this;
    }

    public OrderRoot prepared() throws OrderDomainException {
        if (!status.isConfirmed()) {
            throw new OrderDomainException("Cannot prepare order");
        }
        this.status = OrderStatus.PREPARED;
        return this;
    }

    public OrderRoot startDelivery() throws OrderDomainException {
        if (!status.isPrepared()) {
            throw new OrderDomainException("Cannot deliver order");
        }
        this.status = OrderStatus.DELIVERING;
        return this;
    }

    public OrderRoot completed() throws OrderDomainException {
        if (!status.isDelivering()) {
            throw new OrderDomainException("Cannot complete order");
        }
        this.status = OrderStatus.COMPLETED;
        return this;
    }

    public OrderRoot validateOrder() throws OrderDomainException {
        validateAddress();
        validatePrice();
        return this;
    }

    public OrderRoot cancelByCustomer() {
        cancelType = OrderCancelType.CUSTOMER;
        return this;
    }

    public OrderRoot cancelBySystem() {
        cancelType = OrderCancelType.SYSTEM;
        return this;
    }

    private void validatePrice() throws OrderDomainException {
        Money total = Money.ZERO;
        for (OrderItemRoot orderItem : items) {
            Money orderItemRootPrice = orderItem.getPrice();
            total = total.add(orderItemRootPrice);
        }

        if (price.getAmount().compareTo(total.getAmount()) != 0) {
            throw new OrderDomainException("Order price is greater than total price");
        }
    }

    private void validateAddress() throws OrderDomainException {
        if (this.address == null || !address.isAddressValid()) {
            throw new OrderDomainException("Address is not valid");
        }
    }

}
