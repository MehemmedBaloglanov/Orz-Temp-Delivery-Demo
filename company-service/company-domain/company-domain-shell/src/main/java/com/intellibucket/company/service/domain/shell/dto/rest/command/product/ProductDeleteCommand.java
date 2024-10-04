package com.intellibucket.company.service.domain.shell.dto.rest.command.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductDeleteCommand {

    @JsonProperty("product_id")
    private String productID;

}
