package com.intellibucket.order.service.domain.shell.dto.connectors.company;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductResponse {
    private ProductID productId;
    private CompanyResponse company;
    private Money price;
    private Integer quantity;

    private ProductStatus status;

}
