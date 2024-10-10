package com.intellibucket.order.service.domain.shell.dto.connectors.company;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ProductResponse {
    private ProductID productId;
    private CompanyID companyId;
    private Money price;
    private Integer quantity;
    private ProductStatus productStatus;
    private CompanyStatus companyStatus;

}
