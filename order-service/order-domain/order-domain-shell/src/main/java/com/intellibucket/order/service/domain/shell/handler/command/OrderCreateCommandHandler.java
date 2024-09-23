package com.intellibucket.order.service.domain.shell.handler.command;

import com.food.ordering.system.outbox.OutboxStatus;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.order.service.domain.shell.dto.connectors.CartResponse;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderSagaHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.helper.PaymentOutboxHelper;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.domain.shell.port.output.rest.cart.CartServiceConnectorAdapter;
import com.intellibucket.order.service.domain.shell.port.output.rest.company.CompanyServiceConnectorAdapter;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class OrderCreateCommandHandler {

    private final AbstractSecurityContextHolder securityContextHolder;
    private final CartServiceConnectorAdapter cartServiceConnectorAdapter;
    private final CompanyServiceConnectorAdapter companyServiceConnectorAdapter;
    private final OrderDomainService orderDomainService;
    private final OrderShellMapper orderShellMapper;
    private final OrderRepository orderRepository;
    private final PaymentOutboxHelper paymentOutboxHelper;
    private final OrderSagaHelper orderSagaHelper;

    public OrderResponse handle() throws OrderDomainException {
        UserID userID = this.securityContextHolder.currentUserID();

        List<CartResponse> cartItems = cartServiceConnectorAdapter.findUserCartItems(userID);

        OrderID orderID = OrderID.random();

        Map<ProductID, ProductResponse> productsResponse = companyServiceConnectorAdapter
                .getProductsInformation(
                        cartItems.stream().map(CartResponse::getProductID).toList()
                )
                .stream()
                .collect(Collectors.toMap(ProductResponse::getProductId, Function.identity()));

        List<OrderItemRoot> orderItemRootList = cartItems.stream().map(item -> {
            try {
                return getOrderItemRoot(item, productsResponse, orderID);
            } catch (OrderDomainException e) {
                throw new RuntimeException(e);
            }
        }).toList();

        OrderRoot orderRoot = OrderRoot.builder()
                .id(orderID)
                .items(orderItemRootList)
                // FIXME: Retrieve order address
                //.address()
                .price(orderItemRootList.stream()
                        .map(OrderItemRoot::getSubTotal)
                        .reduce(Money.ZERO, Money::add))
                .build();

        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(orderRoot);
        orderRepository.save(orderRoot);


        paymentOutboxHelper.savePaymentOutboxMessage(
                orderShellMapper.orderCreatedEventToOrderPaymentEventPayload(orderCreatedEvent),
                orderCreatedEvent.getOrderRoot().getStatus(),
                orderSagaHelper.orderStatusToSagaStatus(orderCreatedEvent.getOrderRoot().getStatus()),
                OutboxStatus.STARTED,
                UUID.randomUUID());

        return orderShellMapper.orderRootToOrderResponse(orderRoot);
    }

    private OrderItemRoot getOrderItemRoot(CartResponse item, Map<ProductID, ProductResponse> productsResponse, OrderID orderID) throws OrderDomainException {
        ProductID productID = item.getProductID();
        ProductResponse productResponse = productsResponse.get(productID);
        CompanyID companyID = productResponse.getCompany().getCompanyID();

        if (productResponse.getCompany().getStatus() == CompanyStatus.INACTIVE) {
            log.error("company is not in valid state, companyId: {}", companyID);
            throw new OrderDomainException("company is not in valid state, companyId: " + companyID);
        }
        if (productResponse.getStatus() == ProductStatus.INACTIVE) {
            log.error("product is not in valid state, productId: {}", productID);
            throw new OrderDomainException("product is not in valid state, productId: " + productID);
        }

        Money price = productResponse.getPrice();
        Money subTotal = price.multiply(BigDecimal.valueOf(item.getQuantity()));

        return OrderItemRoot.builder()
                .orderId(orderID)
                .productID(productID)
                .companyID(companyID)
                .quantity(item.getQuantity())
                .price(price)
                .subTotal(subTotal)
                .build();
    }
}
