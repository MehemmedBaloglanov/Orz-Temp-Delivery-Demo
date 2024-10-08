package com.intellibucket.order.service.domain.shell.dto.connectors.cart;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
public class CartResponse {
    private CustomerID customerID;
    private CompanyID companyID;
    private List<CartResponseProduct> products;
}
