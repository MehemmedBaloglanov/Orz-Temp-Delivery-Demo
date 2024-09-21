package com.intellibucket.order.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.cart.service.connector.dto.CartResponse;
import com.intellibucket.company.service.connector.dto.ProductResponse;
import com.intellibucket.order.service.domain.core.event.OrderCreatedEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepositoryAdapter;
import com.intellibucket.order.service.domain.shell.port.output.rest.cart.CartServiceConnectorAdapter;
import com.intellibucket.order.service.domain.shell.port.output.rest.company.CompanyServiceConnectorAdapter;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class OrderCreateCommandHandler {

    private final AbstractSecurityContextHolder securityContextHolder;
    private final CartServiceConnectorAdapter cartServiceConnectorAdapter;
    private final CompanyServiceConnectorAdapter companyServiceConnectorAdapter;
    private final OrderDomainService orderDomainService;
    private final OrderShellMapper orderShellMapper;
    private final OrderRepositoryAdapter orderRepositoryAdapter;

    public OrderResponse handle() throws OrderDomainException {
        UserID userID = this.securityContextHolder.currentUserID();
        List<CartResponse> cartItems = cartServiceConnectorAdapter.findUserCartItems(userID);
        OrderID orderID = OrderID.random();

        Money total = Money.ZERO;
        //FIXME multi request problemini hellet
        List<OrderItemRoot> orderItemRootList = cartItems.stream().map(product -> {

            ProductID productID = product.getProductID();
            ProductResponse productInformation = companyServiceConnectorAdapter.getProductInformation(productID);

            // FIXME burani sorus order event'den sonra mi validasiya olunsun yoxsa once mi?
            //FIXME exception problemini hell et
            if (!productInformation.getIsStock()) {
                try {
                    throw new OrderDomainException("product is not be");
                } catch (OrderDomainException e) {
                    throw new RuntimeException(e);
                }
            }

            Money price = productInformation.getPrice();
            Money subTotal = price.multiply(BigDecimal.valueOf(product.getQuantity()));

            return OrderItemRoot.builder()
                    .orderId(orderID)
                    .productID(productInformation.getProductId())
                    .quantity(product.getQuantity())
                    .companyID(productInformation.getCompanyID())
                    .price(price)
                    .subTotal(subTotal)
                    .build();
        }).toList();


        OrderRoot orderRoot = OrderRoot.builder()
                .id(orderID)
                .items(orderItemRootList)
                // FIXME order address haradan alinacaq fikirles)))
                //.address()
                .price(orderItemRootList.stream().map(OrderItemRoot::getSubTotal).reduce(Money.ZERO, Money::add))
                .build();
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(orderRoot);
        orderRepositoryAdapter.save(orderRoot);
        return orderShellMapper.orderRootToOrderResponse(orderRoot);

    }
}
