package com.intellibucket.cart.service.connector.service;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponse;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCartServiceConnector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartServiceConnector implements AbstractCartServiceConnector {
    @Override
    public List<CartResponse> findUserCartItems(CustomerID customerID) {
        CartResponse cartResponse = CartResponse.builder()
                .customerID(CustomerID.of("01924d1f-a2f8-7bce-833e-d5ef9561d17a"))
                .productID(ProductID.of("97776160-6622-4544-8632-7516546485"))
                .quantity(5)
                .build();
        return List.of(cartResponse);
    }
}
