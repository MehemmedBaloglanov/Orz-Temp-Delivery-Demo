package com.intellibucket.order.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.valueobject.OrderItemStatus;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@Getter
@SuperBuilder
@ToString
public class OrderItemRoot extends AggregateRoot<OrderItemID> {
    private OrderID orderId;
    private CompanyID companyID;
    private final ProductID productID;
    private final Integer quantity;
    private final Money price;
    private final Money subTotal;
    private OrderItemStatus orderItemStatus;

    public void validateInitialize() throws OrderDomainException {
        validateOrderItem();
        this.orderItemStatus = OrderItemStatus.DRAFT;
    }

    public boolean isPriceValid() {
        return price.isGreaterThanZero()
                && price.multiply(BigDecimal.valueOf(quantity)).isEqualTo(subTotal);
    }

    public OrderItemRoot reject() throws OrderDomainException {
        if (! orderItemStatus.isDraft()) {
            log.error("Order item with id: {} status is not DRAFT To reject order with id: {}", this.getRootID(), this.orderId);
            throw new OrderDomainException("Order item with id: " + this.getRootID() + " status is not DRAFT To reject order with id: " + this.orderId);
        }
        this.orderItemStatus = OrderItemStatus.REJECTED;
        log.info("Order with id: {}, item with id: {} is rejected", orderId, this.getRootID());
        return this;
    }

    public OrderItemRoot prepared() throws OrderDomainException {
        if (!orderItemStatus.isDraft()) {
            log.error("Order item with id: {} status is not DRAFT To prepared order with id: {}", this.getRootID(), this.orderId);
            throw new OrderDomainException("Order item with id: " + this.getRootID() + " status is not DRAFT To prepared order with id: " + this.orderId);
        }
        log.info("Order with id: {}, item with id: {} is prepared", orderId, this.getRootID());
        this.orderItemStatus = OrderItemStatus.PREPARED;
        return this;
    }

    private void validateOrderItem() throws OrderDomainException {

        if (orderId == null) {
            throw new OrderDomainException("Order with id: " + this.getRootID() + " has no order id!");
        }
        if (productID == null) {
            throw new OrderDomainException("Order with id: " + this.getRootID() + " has no product id!");
        }
        if (quantity == null || quantity <= 0) {
            throw new OrderDomainException("Order with id: " + this.getRootID() + " has no valid quantity!");
        }
        if (!isPriceValid()) {
            log.error("OrderItem with id: {} has invalid price!", this.getRootID());
            throw new OrderDomainException("OrderItem with id: " + this.getRootID() + " has invalid price!");
        }
    }

}
