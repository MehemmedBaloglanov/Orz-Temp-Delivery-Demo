package com.intellibucket.company.service.domain.shell.dto.rest.command.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class ProductUpdateCommand {
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("product_name")
    private String productName;
    @Positive
    private BigDecimal price;
    @JsonProperty("stock_quantity")
    @Positive
    private Integer stockQuantity;

}
