package com.intellibucket.order.service.domain.shell.dto.connectors.cart;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CartResponseProduct {
    private ProductID productId;
    private Integer quantity;
}
