package com.intellibucket.company.service.domain.shell.dto.rest.command.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCreateCommand {
    private String name;
    private BigDecimal price;
    private String stockQuantity;
}
