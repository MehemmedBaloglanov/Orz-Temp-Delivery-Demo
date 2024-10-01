package com.intellibucket.company.service.domain.shell.dto.rest.command;

import com.intelliacademy.orizonroute.valueobjects.common.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductCreateCommand {
    private final String name;
    private final Money price;
    private final String quantity;
    private final String stockQuantity;


}
