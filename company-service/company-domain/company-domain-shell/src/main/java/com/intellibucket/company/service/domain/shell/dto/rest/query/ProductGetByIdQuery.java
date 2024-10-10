package com.intellibucket.company.service.domain.shell.dto.rest.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetByIdQuery {
    @JsonProperty("product_id")
    private String productId;
}
