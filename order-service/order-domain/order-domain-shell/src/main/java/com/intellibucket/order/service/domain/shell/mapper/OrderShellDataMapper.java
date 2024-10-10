package com.intellibucket.order.service.domain.shell.mapper;

import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCompletedEvent;
import com.intellibucket.order.service.domain.core.event.OrderPaidEvent;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponseProduct;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequest;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequestProduct;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderAddressResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderItemResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.*;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.completed.OrderCompletedEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.completed.OrderCompletedEventProduct;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventAddress;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentRefundEventPayload;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Component
public class OrderShellDataMapper {

    public OrderResponse orderRootToOrderResponse(OrderRoot orderRoot) {
        return OrderResponse.builder()
                .id(orderRoot.getRootID().value().toString())
                .status(orderRoot.getStatus())
                .trackingId(orderRoot.getOrderNumber().value())
                .shippingAddress(orderAddressToOrderAddressResponse(orderRoot.getAddress()))
                .items(orderRoot.getItems().stream().map(this::orderItemToOrderItemResponse).toList())
                .createdAt(orderRoot.getCreatedAt())
                .build();
    }

    private OrderItemResponse orderItemToOrderItemResponse(OrderItemRoot orderItemRoot) {
        return OrderItemResponse.builder()
                .productId(orderItemRoot.getProductID().value())
                .price(orderItemRoot.getPrice().getAmount())
                .quantity(orderItemRoot.getQuantity())
                .subTotal(orderItemRoot.getSubTotal().getAmount())
                .build();
    }

    private OrderAddressResponse orderAddressToOrderAddressResponse(OrderAddress address) {
        return OrderAddressResponse.builder()
                .city(address.getCity())
                .street(address.getStreet())
                .state(address.getState())
                .build();
    }

    public TrackOrderResponse orderRootToTrackOrderResponse(OrderRoot orderRoot) {
        return TrackOrderResponse.builder()
                .orderId(orderRoot.getRootID().value())
                .status(orderRoot.getStatus())
                .build();
    }


    public OrderAddress userAddressToOrderAddress(UserAddress userPrimaryAddress) {
        return OrderAddress.builder()
                .city(userPrimaryAddress.getCity())
                .state(userPrimaryAddress.getState())
                .street(userPrimaryAddress.getStreet())
                .build();
    }

    public OrderPaymentRefundEventPayload orderCancelledEventToOrderPaymentCancelEventPayload(OrderCancelledEvent orderCancelEvent) {
        OrderRoot orderRoot = orderCancelEvent.getOrderRoot();
        return OrderPaymentRefundEventPayload.builder()
                .orderId(orderRoot.getRootID().value())
                .customerId(orderRoot.getCustomerID().value())
                .price(orderRoot.getPrice().getAmount())
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .build();
    }

    public OrderStartDeliveryEventPayload startDeliveryOrderEventToOrderStartDeliveryEventPayload(StartDeliveryOrderEvent startDeliveryOrderEvent) {
        OrderRoot orderRoot = startDeliveryOrderEvent.getOrderRoot();
        return OrderStartDeliveryEventPayload.builder()
                .orderId(orderRoot.getRootID().value())
                .customerId(orderRoot.getCustomerID().value())
                .address(orderAddressToOrderStartDeliveryEventAddress(orderRoot.getAddress()))
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .build();
    }

    public OrderCompanyRefundEventPayload orderCancelledEventToOrderCompanyCancelEventPayload(OrderCancelledEvent orderCancelEvent) {
        OrderRoot orderRoot = orderCancelEvent.getOrderRoot();
        return OrderCompanyRefundEventPayload.builder()
                .orderId(orderRoot.getRootID().value())
                .price(orderRoot.getPrice().getAmount())
                .customerId(orderRoot.getCustomerID().value())
                .products(orderRoot.getItems().stream().map(this::orderRootItemsToOrderCompanyApproveEventProduct).toList())
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .build();
    }

    public OrderCompanyApproveEventPayload orderPaidEventToOrderCompanyApproveEventPayload(OrderPaidEvent orderPaidEvent) {
        OrderRoot orderRoot = orderPaidEvent.getOrderRoot();
        return OrderCompanyApproveEventPayload.builder()
                .orderId(orderRoot.getRootID().value())
                .products(orderRoot.getItems().stream().map(this::orderRootToOrderCompanyApproveEventProduct).toList())
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .build();
    }

    public OrderCompletedEventPayload orderCompletedEventToOrderCompletedEventPayload(OrderCompletedEvent orderCompletedEvent) {
        OrderRoot orderRoot = orderCompletedEvent.getOrderRoot();
        return OrderCompletedEventPayload.builder()
                .orderId(orderRoot.getRootID().value())
                .customerId(orderRoot.getCustomerID().value())
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .products(orderRoot.getItems().stream().map(this::orderItemRootToOrderCompletedEventProduct).toList())
                .build();

    }

    private OrderCompletedEventProduct orderItemRootToOrderCompletedEventProduct(OrderItemRoot orderItemRoot) {
        return OrderCompletedEventProduct.builder()
                .companyId(orderItemRoot.getCompanyID().value())
                .productId(orderItemRoot.getProductID().value())
                .price(orderItemRoot.getPrice().getAmount())
                .subTotal(orderItemRoot.getSubTotal().getAmount())
                .build();
    }

    private OrderStartDeliveryEventAddress orderAddressToOrderStartDeliveryEventAddress(OrderAddress address) {
        return OrderStartDeliveryEventAddress.builder()
                .state(address.getState())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }

    private OrderCompanyApproveEventProduct orderRootItemsToOrderCompanyApproveEventProduct(OrderItemRoot orderItemRoot) {
        return OrderCompanyApproveEventProduct.builder()
                .companyId(orderItemRoot.getCompanyID().value())
                .productId(orderItemRoot.getProductID().value())
                .quantity(orderItemRoot.getQuantity())
                .build();
    }

    private OrderCompanyApproveEventProduct orderRootToOrderCompanyApproveEventProduct(OrderItemRoot orderItemRoot) {
        return OrderCompanyApproveEventProduct.builder()
                .companyId(orderItemRoot.getCompanyID().value())
                .productId(orderItemRoot.getProductID().value())
                .quantity(orderItemRoot.getQuantity())
                .build();
    }

    public CompanyRequest cartResponseToCompanyResponse(CartResponse item) {
        return CompanyRequest.builder()
                .companyID(item.getCompanyID())
                .products(item.getProducts().stream().map(this::cartResponseProductToCompanyResponseProduct).toList())
                .build();
    }

    public CompanyRequestProduct cartResponseProductToCompanyResponseProduct(CartResponseProduct cartResponseProduct) {
        return CompanyRequestProduct.builder()
                .productID(cartResponseProduct.getProductId())
                .quantity(cartResponseProduct.getQuantity())
                .build();

    }
}
