package com.intellibucket.cart.service.connector.service.adapter;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponse;
import com.intellibucket.order.service.domain.shell.dto.connectors.cart.CartResponseProduct;
import com.intellibucket.order.service.domain.shell.port.output.connector.AbstractCartServiceConnector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestCartServiceConnector implements AbstractCartServiceConnector {
    @Override
    public List<CartResponse> getUserCart(CustomerID customerID) {

        CartResponseProduct cartResponseProduct1 = CartResponseProduct.builder()
                .productId(ProductID.of("97776160-6622-4544-8632-7516546485"))
                .quantity(3)
                .build();

        CartResponseProduct cartResponseProduct2 = CartResponseProduct.builder()
                .productId(ProductID.of("97776160-6622-4544-4732-7516546485"))
                .quantity(2)
                .build();

        CartResponse cartResponse = CartResponse.builder()
                .customerID(CustomerID.of("01924d1f-a2f8-7bce-833e-d5ef9561d17a"))
                .companyID(CompanyID.of("97776160-6622-4544-8632-7516546485"))
                .products(List.of(cartResponseProduct1, cartResponseProduct2))
                .build();
        return List.of(cartResponse);
    }
}
