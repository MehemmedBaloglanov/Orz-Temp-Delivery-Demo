package com.intellibucket.company.service.domain.shell.dto.rest.command.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.index.qual.Positive;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateCommand {

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_name")
    private String productName;

    @Positive
    private BigDecimal price;

    @Positive
    @JsonProperty("stock_quantity")
    private String stockQuantity;

}
