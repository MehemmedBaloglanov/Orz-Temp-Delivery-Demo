package com.intellibucket.company.service.domain.shell.handler.product.command;

import com.intellibucket.company.service.domain.core.event.product.ProductCreatedEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCreateCommandHandler {
    private final ProductRepositoryAdapter productRepository;
    private  final ProductShellDataMapper productShellDataMapper;
    private final ProductDomainService productDomainService;

    @Transactional
    public ProductResponse handle(ProductCreateCommand command) throws CompanyDomainException {
        ProductRoot productRoot = productShellDataMapper.productCreateCommandToProductRoot(command);
        ProductCreatedEvent product = productDomainService.createProduct(productRoot);
        log.info("Product created");
        ProductRoot productRootSave = productRepository.save(product.getProductRoot());
        log.info("Product saved");
        if (productRootSave == null) {
            throw new CompanyDomainException("Error saving product");
        }
        return productShellDataMapper.productRootToProductResponse(productRootSave);
    }
}
