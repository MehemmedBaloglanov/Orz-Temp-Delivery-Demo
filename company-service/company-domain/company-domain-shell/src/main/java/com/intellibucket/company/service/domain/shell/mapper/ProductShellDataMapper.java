package com.intellibucket.company.service.domain.shell.mapper;

import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.connector.ProductResponseForOrder;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;

public class ProductShellDataMapper {
    public ProductRoot productCreateCommandToProductRoot(ProductCreateCommand command) {
        return ProductRoot.builder()
                .price(command.getPrice())
                .name(command.getName())
                .stockQuantity(Integer.valueOf(command.getStockQuantity()))
                .build();
    }

    public ProductResponse productRootToProductResponse(ProductRoot productRootSave) {
        return ProductResponse.builder()
                .id(productRootSave.getRootID().toString())
                .companyId(productRootSave.getCompanyID().toString())
                .price(productRootSave.getPrice())
                .name(productRootSave.getName())
                .stockQuantity(productRootSave.getStockQuantity())
                .status(productRootSave.getStatus().toString())
                .build();
    }

}
