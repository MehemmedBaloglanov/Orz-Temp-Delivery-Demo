package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.event.product.ProductCreatedEvent;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductCreateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.mapper.ProductShellDataMapper;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCreateCommandHandler {
    private final ProductRepositoryAdapter productRepository;
    private  final ProductShellDataMapper productShellDataMapper;
    private final ProductDomainService productDomainService;

    public ProductResponse handle(ProductCreateCommand command) throws ValidateException {
        ProductID productID = ProductID.random();

        ProductRoot productRoot = productShellDataMapper.productCreateCommandToProductRoot(command);
        productRoot.setId(productID);
        ProductCreatedEvent product = productDomainService.createProduct(productRoot);
        ProductRoot productRootSave = productRepository.save(product.getProductRoot());
        if (productRootSave == null) {
            throw new RuntimeException("Error saving product");
        }
        return productShellDataMapper.productRootToProductResponse(productRootSave);
    }
}
