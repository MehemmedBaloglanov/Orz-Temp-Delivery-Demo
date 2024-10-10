package com.intellibucket.company.service.domain.shell.dto.rest.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductGetByIdQuery {
    private String productId;
}
