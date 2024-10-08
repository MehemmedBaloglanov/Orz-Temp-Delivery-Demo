package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
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
import com.intellibucket.order.service.domain.shell.dto.connectors.company.request.CompanyRequest;
import com.intellibucket.order.service.domain.shell.dto.connectors.user.UserAddress;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellDataMapper;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCartServiceConnector;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCompanyServiceConnector;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractUserServiceConnector;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateCommandHandler {

    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderDomainService orderDomainService;
    private final OrderShellDataMapper orderShellDataMapper;
    private final OrderRepositoryHelper orderRepositoryHelper;

    private final AbstractCartServiceConnector cartServiceConnector;
    private final AbstractCompanyServiceConnector companyServiceConnector;
    private final AbstractUserServiceConnector userServiceConnector;

    @Transactional

    public OrderResponse handle() throws OrderDomainException {
        CustomerID customerID = this.securityContextHolder.currentCustomerID();
        OrderID orderID = OrderID.random();

        List<CartResponse> cartItems = getUserCartItems(customerID);
        List<ProductResponse> productsResponses = getProducts(cartItems);

        List<OrderItemRoot> orderItemRoots = createOrderItems(productsResponses, orderID);
        OrderRoot orderRoot = createOrder(orderID, customerID, orderItemRoots);

        orderRepositoryHelper.saveOrder(orderRoot);
        return orderShellDataMapper.orderRootToOrderResponse(orderRoot);
    }

    private List<CartResponse> getUserCartItems(CustomerID customerID) throws OrderDomainException {
        List<CartResponse> cartItems = cartServiceConnector.getUserCart(customerID);
        validateCartItems(cartItems);
        return cartItems;
    }

    private OrderAddress fetchUserPrimaryAddress(CustomerID customerID) throws OrderDomainException {
        UserAddress userPrimaryAddress = userServiceConnector.getUserPrimaryAddress(customerID);
        if (userPrimaryAddress == null || userPrimaryAddress.getStreet() == null || userPrimaryAddress.getCity() == null || userPrimaryAddress.getState() == null) {
            throw new OrderDomainException("User primary address not found.");
        }
        return orderShellDataMapper.userAddressToOrderAddress(userPrimaryAddress);
    }

    private List<ProductResponse> getProducts(List<CartResponse> cartItems) throws OrderDomainException {
        List<CompanyRequest> companyRequests = cartItems.stream().map(orderShellDataMapper::cartResponseToCompanyResponse).toList();

        List<ProductResponse> productResponses = companyServiceConnector.getProductsInformation(companyRequests);

        for (ProductResponse productResponse : productResponses) {
            validateProductResponse(productResponse);
        }
        log.debug("Fetched products information: {}", productResponses);
        return productResponses;
    }

    private OrderRoot createOrder(OrderID orderID, CustomerID customerID, List<OrderItemRoot> orderItemRootList) throws OrderDomainException {
        Money totalPrice = orderItemRootList.stream().map(OrderItemRoot::getSubTotal).reduce(Money.ZERO, Money::add);
        OrderRoot orderRoot = OrderRoot.builder()
                .id(orderID)
                .customerID(customerID)
                .items(orderItemRootList)
                .address(fetchUserPrimaryAddress(customerID))
                .price(totalPrice)
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .build();

        orderDomainService.validateAndInitiateOrder(orderRoot);
        return orderRoot;
    }

    private List<OrderItemRoot> createOrderItems(List<ProductResponse> productResponses, OrderID orderID) throws OrderDomainException {

        List<OrderItemRoot> orderItemRootList = new ArrayList<>();

        for (ProductResponse productResponse : productResponses) {
            Money price = productResponse.getPrice();
            Money subTotal = price.multiply(BigDecimal.valueOf(productResponse.getQuantity()));

            OrderItemRoot orderItemRoot = OrderItemRoot.builder()
                    .id(OrderItemID.random())
                    .orderId(orderID)
                    .productID(productResponse.getProductId())
                    .companyID(productResponse.getCompanyId())
                    .quantity(productResponse.getQuantity())
                    .price(price)
                    .subTotal(subTotal)
                    .build();
            orderItemRoot.validateInitialize();
            orderItemRootList.add(orderItemRoot);
        }

        return orderItemRootList;
    }

    private void validateCartItems(List<CartResponse> cartItems) throws OrderDomainException {
        if (cartItems == null || cartItems.isEmpty()) {
            throw new OrderDomainException("User cart is empty cannot proceed with order creation");
        }
    }

    private void validateProductResponse(ProductResponse productResponse) throws OrderDomainException {

        if (productResponse.getProductStatus() == ProductStatus.OUT_OF_STOCK) {
            log.error("Product out of stock with id: {}", productResponse.getProductId());
            throw new OrderDomainException("Product out of stock with id: " + productResponse.getProductId());
        }
        if (productResponse.getProductStatus() == ProductStatus.NOT_FOUND) {
            log.error("Product not found with id: {}", productResponse.getProductId());
            throw new OrderDomainException("Product not found with id: " + productResponse.getProductId());
        }
        if (productResponse.getProductStatus() != ProductStatus.ACTIVE) {
            log.error("Product is not active with id: {}", productResponse.getProductId());
            throw new OrderDomainException("Product is not active with id: " + productResponse.getProductId());
        }

        if (productResponse.getCompanyStatus() == CompanyStatus.INACTIVE) {
            log.error("Company status is not active: {}", productResponse.getCompanyId());
            throw new OrderDomainException("Company status is not active: " + productResponse.getCompanyId());
        }

        if (productResponse.getCompanyStatus() == CompanyStatus.NOT_FOUND) {
            log.error("Company not found: {}", productResponse.getCompanyId());
            throw new OrderDomainException("Company not found: " + productResponse.getCompanyId());
        }
        if (productResponse.getCompanyStatus() != CompanyStatus.ACTIVE) {
            log.error("Company status is not active: {}", productResponse.getCompanyId());
            throw new OrderDomainException("Company status is not active: " + productResponse.getCompanyId());
        }

    }
}
