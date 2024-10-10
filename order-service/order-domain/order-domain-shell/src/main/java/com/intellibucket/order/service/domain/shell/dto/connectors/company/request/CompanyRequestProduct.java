package com.intellibucket.order.service.domain.shell.dto.connectors.company.request;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CompanyRequestProduct {
    private ProductID productID;
    private Integer quantity;
}
