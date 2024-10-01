package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.company.service.domain.core.event.product.ProductCreatedEvent;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCreateCommandHandler {
    private final AbstractSecurityContextHolder securityContextHolder;
    private final ProductRepositoryAdapter productRepository;
    private final ProductShellDataMapper productShellDataMapper;
    private final ProductDomainService productDomainService;

    public ProductResponse handle(ProductCreateCommand command) throws ValidateException {
        ProductID productID = ProductID.random();

        ProductRoot productRoot = productShellDataMapper.productCreateCommandToProductRoot(command);
        productRoot.setId(productID);
        CompanyID companyID = this.securityContextHolder.currentCompanyID();
        productRoot.setCompanyID(companyID);
        ProductCreatedEvent product = productDomainService.createProduct(productRoot);
        ProductRoot productRootSave = productRepository.save(product.getProductRoot());
        if (productRootSave == null) {
            throw new RuntimeException("Error saving product");
        }
        return productShellDataMapper.productRootToProductResponse(productRootSave);
    }
}
