package com.intellibucket.company.service.domain.shell.dto.rest.connector;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.order.service.domain.shell.dto.connectors.company.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponseForOrder {

    private ProductID productID;

    private Money price;

    private Integer stockQuantity;

    private ProductStatus status;

}
