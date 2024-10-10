package com.intellibucket.company.service.domain.shell.mapper;

import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductShellDataMapper {
    public ProductRoot productCreateCommandToProductRoot(ProductCreateCommand command) {
        return ProductRoot.builder()
                .price(Money.of(command.getPrice().intValue()).validatePrice())
                .name(command.getName())
                .stockQuantity(Integer.valueOf(command.getStockQuantity()))
                .build();
    }

    public ProductResponse productRootToProductResponse(ProductRoot productRoot) {
        return ProductResponse.builder()
                .id(productRoot.getRootID().value().toString())
//                .companyId(productRootSave.getCompanyID().toString())
                .price(productRoot.getPrice())
                .name(productRoot.getName())
                .stockQuantity(productRoot.getStockQuantity())
                .status(productRoot.getStatus().toString())
                .build();
    }

}
