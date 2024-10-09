package com.intellibucket.company.service.domain.shell.dto.rest.command.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProductStatusCommand {
    @JsonProperty("product_id")
    private String productId;
}
