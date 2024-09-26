package com.intellibucket.company.service.domain.shell.dto.rest.response;

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

    private final ProductID productID;

    private final String name;

    private final Money price;

    private final CompanyID companyID;

    private final String quantity;

    private final String stockQuantity;

    private final ProductStatus status;

    private final OffsetDateTime createdAt;


}
