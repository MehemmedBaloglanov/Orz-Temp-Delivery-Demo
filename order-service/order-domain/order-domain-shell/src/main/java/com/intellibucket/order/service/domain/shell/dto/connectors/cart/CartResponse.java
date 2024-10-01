package com.intellibucket.order.service.domain.shell.dto.connectors.cart;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CartResponse {
    private UserID userID;
    private ProductID productID;
    private Integer quantity;
}
