package com.intellibucket.order.service.repository.mapper;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.constants.DomainConstants;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.repository.model.OrderEntity;
import com.intellibucket.order.service.repository.model.OrderItemEntity;
import com.intellibucket.order.service.repository.model.OrderJpaAddress;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessMapper {

    public OrderEntity orderRootToOrderEntity(OrderRoot orderRoot) {

        return OrderEntity.builder()
                .id(orderRoot.getRootID().value())
                .orderNumber(orderRoot.getOrderNumber().value())
                .address(orderAddressToAddressJpaEntity(orderRoot.getAddress()))
                .price(orderRoot.getPrice().getAmount())
                .items(orderItemRootToOrderItemEntities(orderRoot.getItems()))
                .orderStatus(orderRoot.getStatus())
                .createdAt(OffsetDateTime.now(DomainConstants.ZONE_ID))//Fixme add created time in aggregate root
                .updatedAt(OffsetDateTime.now(DomainConstants.ZONE_ID))
                .version(orderRoot.getVersion().toShort())
                .build();
    }

    public OrderRoot orderEntityToOrderRoot(OrderEntity orderEntity) {
        return OrderRoot.builder()
                .userId(UserID.of(orderEntity.getId()))
                .address(orderJpaAddressToOrderAddress(orderEntity.getAddress()))
                .items(orderItemEntitiesToOrderItemRoot(orderEntity.getItems()))
                .price(Money.of(orderEntity.getPrice()))
                .orderNumber(OrderNumber.of(orderEntity.getOrderNumber()))
                .status(orderEntity.getOrderStatus())
                .build();
    }

    private List<OrderItemRoot> orderItemEntitiesToOrderItemRoot(List<OrderItemEntity> items) {
        return items.stream()
                .map(orderItem -> OrderItemRoot.builder()
                        .orderId(OrderID.of(orderItem.getOrderItemId()))
                        .productID(ProductID.of(orderItem.getProductID()))
                        .quantity(orderItem.getQuantity())
                        .price(Money.of(orderItem.getPrice()))
                        .subTotal(Money.of(orderItem.getSubTotal()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<OrderItemEntity> orderItemRootToOrderItemEntities(List<OrderItemRoot> items) {
        return items.stream()
                .map(orderItemRoot -> OrderItemEntity.builder()
                        .orderItemId(orderItemRoot.getOrderId().value())
                        .productID(orderItemRoot.getProductID().value())
                        .quantity(orderItemRoot.getQuantity())
                        .price(orderItemRoot.getPrice().getAmount())
                        .subTotal(orderItemRoot.getSubTotal().getAmount())
                        .build())
                .collect(Collectors.toList());
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

}
