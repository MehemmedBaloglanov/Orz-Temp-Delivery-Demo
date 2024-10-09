package com.intellibucket.company.service.domain.shell.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
@AllArgsConstructor
public class ProductResponse {

    private final String id;

    private final String name;

    private final Money price;

//    @JsonProperty("company_id")
//    private final String companyId;

    @JsonProperty("stock_quantity")
    private final Integer stockQuantity;

    private final String status;


}
