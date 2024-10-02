package com.intellibucket.order.service.repository.mapper;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.repository.helper.OrderRepositoryDataHelper;
import com.intellibucket.order.service.repository.model.order.OrderEntity;
import com.intellibucket.order.service.repository.model.order.OrderItemEntity;
import com.intellibucket.order.service.repository.model.order.OrderJpaAddress;
import com.intellibucket.order.service.repository.model.outbox.OutboxJpaEntity;
import com.intellibucket.order.service.repository.model.outbox.OutboxJpaStatus;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDataAccessMapper {

    private final OrderRepositoryDataHelper orderRepositoryDataHelper;

    public OrderEntity orderRootToOrderEntity(OrderRoot orderRoot) throws OrderDomainException {
        log.debug("OrderDataAccessMapper Converting OrderRoot to OrderEntity: {}", orderRoot);
        OrderEntity orderEntity = OrderEntity.builder()
                .id(orderRoot.getRootID().value())
                .customerId(orderRoot.getCustomerID().value())
                .orderNumber(orderRoot.getOrderNumber().value())
                .address(orderAddressToAddressJpaEntity(orderRoot.getAddress()))
                .price(orderRoot.getPrice().getAmount())
                .items(orderItemRootToOrderItemEntity(orderRoot.getItems()))
                .orderStatus(orderRepositoryDataHelper.orderStatusToOrderJpaStatus(orderRoot.getStatus()))
                .version(orderRoot.getVersion().toShort())
                .createdAt(orderRoot.getCreatedAt())
                .failureMessage(orderRoot.getFailureMessage())
                .cancellationType(orderRoot.getCancelType() == null ? null : orderRepositoryDataHelper.orderCancelTypeToOrderCancellationJpaType(orderRoot.getCancelType()))
                .build();

        orderEntity.getItems().forEach(item -> item.setOrder(orderEntity));
        return orderEntity;
    }

    public OrderRoot orderEntityToOrderRoot(OrderEntity orderEntity) throws OrderDomainException {
        return OrderRoot.builder()
                .id(OrderID.of(orderEntity.getId()))
                .customerID(CustomerID.of(orderEntity.getId()))
                .address(orderJpaAddressToOrderAddress(orderEntity.getAddress()))
                .items(orderItemEntitiesToOrderItemRoot(orderEntity.getItems()))
                .price(Money.of(orderEntity.getPrice()))
                .orderNumber(OrderNumber.of(orderEntity.getOrderNumber()))
                .status(orderRepositoryDataHelper.orderJpaStatusToOrderStatus(orderEntity.getOrderStatus()))
                .cancelType(orderEntity.getCancellationType() == null ? null : orderRepositoryDataHelper.orderCancellationJpaTypeToOrderCancelType(orderEntity.getCancellationType()))
                .build();
    }

    private List<OrderItemRoot> orderItemEntitiesToOrderItemRoot(List<OrderItemEntity> orderItemsEntity) throws OrderDomainException {
        List<OrderItemRoot> orderItemEntities = new ArrayList<>();
        for (OrderItemEntity orderItemEntity : orderItemsEntity) {
            OrderItemRoot orderItemRoot = OrderItemRoot.builder()
                    .id(OrderItemID.of(orderItemEntity.getId()))
                    .orderId(OrderID.of(orderItemEntity.getOrder().getId()))
                    .productID(ProductID.of(orderItemEntity.getProductId()))
                    .companyID(CompanyID.of(orderItemEntity.getCompanyId()))
                    .quantity(orderItemEntity.getQuantity())
                    .price(Money.of(orderItemEntity.getPrice()))
                    .subTotal(Money.of(orderItemEntity.getSubTotal()))
                    .orderItemStatus(orderRepositoryDataHelper.orderItemJpaStatusToOrderItemStatus(orderItemEntity.getStatus()))
                    .build();
            orderItemEntities.add(orderItemRoot);
        }
        return orderItemEntities;

    }

    private List<OrderItemEntity> orderItemRootToOrderItemEntity(List<OrderItemRoot> orderItemsRoot) throws OrderDomainException {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        for (OrderItemRoot orderItemRoot : orderItemsRoot) {
            OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                    .id(orderItemRoot.getRootID().value())
                    .productId(orderItemRoot.getProductID().value())
                    .companyId(orderItemRoot.getCompanyID().value())
                    .quantity(orderItemRoot.getQuantity())
                    .price(orderItemRoot.getPrice().getAmount())
                    .subTotal(orderItemRoot.getSubTotal().getAmount())
                    .status(orderRepositoryDataHelper.orderItemJStatusToOrderItemJpaStatus(orderItemRoot.getOrderItemStatus()))
                    .build();
            orderItemEntities.add(orderItemEntity);
        }
        return orderItemEntities;
    }

    private OrderAddress orderJpaAddressToOrderAddress(OrderJpaAddress address) {
        return OrderAddress.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .build();
    }

    private OrderJpaAddress orderAddressToAddressJpaEntity(OrderAddress address) {
        return OrderJpaAddress.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .build();
    }


    public OutboxJpaEntity outboxMessageToOutboxJpaEntity(OutboxMessage outboxMessage) throws OrderDomainException {
        return OutboxJpaEntity.builder()
                .id(outboxMessage.getId())
                .sagaName(outboxMessage.getSagaName())
                .payload(outboxMessage.getPayload())
                .createdAt(outboxMessage.getCreatedAt())
                .processedAt(outboxMessage.getProcessedAt())
                .outboxStatus(orderRepositoryDataHelper.outboxStatusToOutboxJpaStatus(outboxMessage.getOutboxStatus()))
                .build();
    }

    public OutboxMessage outboxJpaEntityToOutboxMessage(OutboxJpaEntity outboxJpaEntity) throws OrderDomainException {
        return OutboxMessage.builder()
                .id(outboxJpaEntity.getId())
                .sagaName(outboxJpaEntity.getSagaName())
                .payload(outboxJpaEntity.getPayload())
                .createdAt(outboxJpaEntity.getCreatedAt())
                .processedAt(outboxJpaEntity.getProcessedAt())
                .outboxStatus(orderRepositoryDataHelper.outboxJpaStatusToOutboxStatus(outboxJpaEntity.getOutboxStatus()))
                .build();
    }
}
