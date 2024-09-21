package com.intellibucket.cart.service.connector.dto;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
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
    private CompanyID companyID;
}
