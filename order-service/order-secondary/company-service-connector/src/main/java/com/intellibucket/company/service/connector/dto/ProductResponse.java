package com.intellibucket.company.service.connector.dto;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
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
    private CompanyID companyID;
    private Money price;
    private Boolean isStock;

}
