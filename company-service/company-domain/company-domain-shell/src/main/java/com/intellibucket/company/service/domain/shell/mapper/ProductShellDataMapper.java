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
                .price(Money.of(command.getPrice()))
                .name(command.getName())
                .stockQuantity(Integer.valueOf(command.getStockQuantity()))
                .build();
    }

    //todo burada id ni yene qaytarmaga ehtiyac varmi?
    public ProductResponse productRootToProductResponse(ProductRoot productRootSave) {
        return ProductResponse.builder()
                .id(productRootSave.getRootID().value().toString())
//                .companyId(productRootSave.getCompanyID().toString())
                .price(productRootSave.getPrice())
                .name(productRootSave.getName())
                .stockQuantity(productRootSave.getStockQuantity())
                .status(productRootSave.getStatus().toString())
                .build();
    }

}
