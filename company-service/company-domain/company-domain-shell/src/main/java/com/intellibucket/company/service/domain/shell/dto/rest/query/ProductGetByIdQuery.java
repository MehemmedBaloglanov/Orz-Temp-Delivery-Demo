package com.intellibucket.company.service.domain.shell.dto.rest.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductGetByIdQuery {
    private String productId;
}
