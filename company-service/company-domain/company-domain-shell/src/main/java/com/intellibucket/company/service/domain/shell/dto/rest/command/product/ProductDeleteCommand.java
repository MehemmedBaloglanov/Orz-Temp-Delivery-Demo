package com.intellibucket.company.service.domain.shell.dto.rest.command.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDeleteCommand {

    private String productID;

}
