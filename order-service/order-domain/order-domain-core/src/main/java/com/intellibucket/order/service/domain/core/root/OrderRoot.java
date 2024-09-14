package com.intellibucket.order.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class OrderRoot extends AggregateRoot<OrderID> {


    private final UserID userId;
    private final OrderAddress address;
    private final List<OrderItemRoot> items;
    private final Money price;
    private OrderNumber orderNumber;
    private OrderStatus status;


    public OrderRoot initializeOrder() {
        setId(OrderID.random());
        orderNumber = OrderNumber.generate();
        status = OrderStatus.CREATED;
        return this;
    }

    public OrderRoot initCancel() throws OrderDomainException {
        if (status.isDelivering() || status.isCompleted() || status.isCancelled()) {
            throw new OrderDomainException("Order is not in correct state for the initCancel operation!");
        }

        this.status = OrderStatus.CANCELLING;

        return this;
    }

    public OrderRoot cancel() throws OrderDomainException {
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
    public OrderRoot assign() throws OrderDomainException {
        if (!status.isPaid()) {
            throw new OrderDomainException("Cannot assign order");
        }
        this.status = OrderStatus.ASSIGNED;
        return this;
    }

    public OrderRoot initPrepare() throws OrderDomainException {
        if (!status.isAssigned()) {
            throw new OrderDomainException("Cannot prepare order");
        }
        this.status = OrderStatus.PREPARING;
        return this;
    }

    public OrderRoot prepare() throws OrderDomainException {
        if (!status.isPreparing()) {
            throw new OrderDomainException("Cannot prepare order");
        }
        this.status = OrderStatus.PREPARED;
        return this;
    }

    public OrderRoot delivering() throws OrderDomainException {
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

    private void validatePrice() throws OrderDomainException {
        Money total = items.stream()
                .peek(orderItem -> {
                    if (!orderItem.isPriceValid()) {
                        try {
                            throw new OrderDomainException("Invalid order item: " + orderItem);
                        } catch (OrderDomainException e) {
                            throw new RuntimeException(e);
                        }
                    }
                })
                .map(OrderItemRoot::getPrice)
                .reduce(Money.ZERO, Money::add);

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
