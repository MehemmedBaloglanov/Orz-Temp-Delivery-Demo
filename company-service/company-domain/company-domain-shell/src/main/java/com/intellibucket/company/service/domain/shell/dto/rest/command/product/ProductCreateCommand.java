package com.intellibucket.company.service.domain.shell.dto.rest.command.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ProductCreateCommand {
    private final String name;
    private final BigDecimal price;
    private final String stockQuantity;
}
