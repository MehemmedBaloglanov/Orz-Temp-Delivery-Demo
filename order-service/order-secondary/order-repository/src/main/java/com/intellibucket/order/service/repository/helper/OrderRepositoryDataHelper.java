package com.intellibucket.order.service.repository.helper;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.valueobject.OrderCancelType;
import com.intellibucket.order.service.domain.core.valueobject.OrderItemStatus;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import com.intellibucket.order.service.repository.model.order.OrderCancellationJpaType;
import com.intellibucket.order.service.repository.model.order.OrderItemJpaStatus;
import com.intellibucket.order.service.repository.model.order.OrderJpaStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryDataHelper {

    public OrderJpaStatus orderStatusToOrderJpaStatus(OrderStatus orderStatus) throws OrderDomainException {
        return switch (orderStatus) {
            case COMPLETED -> OrderJpaStatus.CREATED;
            case APPROVED -> OrderJpaStatus.APPROVED;
            case CANCELLING -> OrderJpaStatus.CANCELLING;
            case CANCELLED -> OrderJpaStatus.CANCELLED;
            case PAID -> OrderJpaStatus.PAID;
            case CONFIRMED -> OrderJpaStatus.CONFIRMED;
            case PREPARED -> OrderJpaStatus.PREPARED;
            case DELIVERING -> OrderJpaStatus.DELIVERING;
            default -> throw new OrderDomainException("Unsupported OrderStatus: " + orderStatus);
        };
    }

    public OrderStatus orderJpaStatusToOrderStatus(OrderJpaStatus orderStatus) throws OrderDomainException {
        return switch (orderStatus) {
            case COMPLETED -> OrderStatus.CREATED;
            case APPROVED -> OrderStatus.APPROVED;
            case CANCELLING -> OrderStatus.CANCELLING;
            case CANCELLED -> OrderStatus.CANCELLED;
            case PAID -> OrderStatus.PAID;
            case CONFIRMED -> OrderStatus.CONFIRMED;
            case PREPARED -> OrderStatus.PREPARED;
            case DELIVERING -> OrderStatus.DELIVERING;
            default -> throw new OrderDomainException("Unsupported OrderStatus: " + orderStatus);
        };
    }

    public OrderItemStatus orderItemJpaStatusToOrderItemStatus(OrderItemJpaStatus status) throws OrderDomainException {
        return switch (status) {
            case REJECTED -> OrderItemStatus.REJECTED;
            case DRAFT -> OrderItemStatus.DRAFT;
            case PREPARED -> OrderItemStatus.PREPARED;
            case CONFIRMED -> OrderItemStatus.CONFIRMED;
            default -> throw new OrderDomainException("Unsupported OrderItemStatus: " + status);
        };
    }

    public OrderItemJpaStatus orderItemJStatusToOrderItemJpaStatus(OrderItemStatus status) throws OrderDomainException {
        return switch (status) {
            case REJECTED -> OrderItemJpaStatus.REJECTED;
            case DRAFT -> OrderItemJpaStatus.DRAFT;
            case PREPARED -> OrderItemJpaStatus.PREPARED;
            case CONFIRMED -> OrderItemJpaStatus.CONFIRMED;
            default -> throw new OrderDomainException("Unsupported OrderItemStatus: " + status);
        };
    }

    public OrderCancellationJpaType orderCancelTypeToOrderCancellationJpaType(OrderCancelType cancelType) throws OrderDomainException {
        return switch (cancelType) {
            case SYSTEM -> OrderCancellationJpaType.SYSTEM;
            case CUSTOMER -> OrderCancellationJpaType.CUSTOMER;
            case COMPANY -> OrderCancellationJpaType.COMPANY;
            default -> throw new OrderDomainException("Unsupported OrderCancelType: " + cancelType);
        };
    }

    public OrderCancelType orderCancellationJpaTypeToOrderCancelType(OrderCancellationJpaType cancelType) throws OrderDomainException {
        return switch (cancelType) {
            case SYSTEM -> OrderCancelType.SYSTEM;
            case CUSTOMER -> OrderCancelType.CUSTOMER;
            case COMPANY -> OrderCancelType.COMPANY;
            default -> throw new OrderDomainException("Unsupported OrderCancelType: " + cancelType);
        };
    }
}
