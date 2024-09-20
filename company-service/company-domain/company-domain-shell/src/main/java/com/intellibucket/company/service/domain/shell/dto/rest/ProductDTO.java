package com.intellibucket.company.service.domain.shell.dto.rest;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private ProductID id;
    private String name;
    private String description;
    private Money price;
    private Integer quantity;
    private String status;
    private CompanyID companyId;
}

