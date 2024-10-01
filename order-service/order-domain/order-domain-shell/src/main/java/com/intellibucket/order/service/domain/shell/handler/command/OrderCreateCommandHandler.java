package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.CompanyStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCartServiceConnector;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCompanyServiceConnector;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractUserServiceConnector;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateCommandHandler {

    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderDomainService orderDomainService;
    private final OrderShellMapper orderShellMapper;
    private final OrderRepositoryHelper orderRepositoryHelper;

    private final AbstractCartServiceConnector cartServiceConnector;
    private final AbstractCompanyServiceConnector companyServiceConnector;
    private final AbstractUserServiceConnector userServiceConnector;

    @Transactional

    public OrderResponse handle() throws OrderDomainException {
        UserID userID = this.securityContextHolder.currentUserID();

        List<CartResponse> cartItems = cartServiceConnector.findUserCartItems(userID);
        OrderID orderID = OrderID.random();

        Map<ProductID, ProductResponse> productsResponse = fetchProducts(cartItems);

        List<OrderItemRoot> orderItemRootList = new ArrayList<>();

        for (CartResponse item : cartItems) {
            OrderItemRoot orderItemRoot = getOrderItemRoot(item, productsResponse, orderID);
            orderItemRoot.validateInitialize();
            orderItemRootList.add(orderItemRoot);
        }

        OrderRoot orderRoot = OrderRoot.builder()
                .id(orderID)
                .userId(userID)
                .items(orderItemRootList)
                .address(fetchUserPrimaryAddress(userID))
                .price(orderItemRootList.stream()
                        .map(OrderItemRoot::getSubTotal)
                        .reduce(Money.ZERO, Money::add))
                .build();

        orderDomainService.validateAndInitiateOrder(orderRoot);
        orderRepositoryHelper.saveOrder(orderRoot);
        return orderShellMapper.orderRootToOrderResponse(orderRoot);
    }


    private OrderAddress fetchUserPrimaryAddress(UserID userID) {
        UserAddress userPrimaryAddress = userServiceConnector.getUserPrimaryAddress(userID);
        return orderShellMapper.userAddressToOrderAddress(userPrimaryAddress);
    }

    private Map<ProductID, ProductResponse> fetchProducts(List<CartResponse> cartItems) {
        return companyServiceConnector
                .getProductsInformation(cartItems.stream().map(CartResponse::getProductID).toList())
                .stream()
                .collect(Collectors.toMap(ProductResponse::getProductId, Function.identity()));
    }

    private OrderItemRoot getOrderItemRoot(CartResponse item,
                                           Map<ProductID, ProductResponse> productsResponse,
                                           OrderID orderID) throws OrderDomainException {

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
                .id(OrderItemID.random())
                .orderId(orderID)
                .productID(productID)
                .companyID(companyID)
                .quantity(item.getQuantity())
                .price(price)
                .subTotal(subTotal)
                .build();
    }
}
