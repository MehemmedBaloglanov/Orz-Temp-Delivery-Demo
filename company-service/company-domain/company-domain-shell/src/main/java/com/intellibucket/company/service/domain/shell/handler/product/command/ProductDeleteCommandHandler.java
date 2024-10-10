package com.intellibucket.company.service.domain.shell.handler.product.command;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductDeleteCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Slf4j
@Component
@AllArgsConstructor
public class ProductDeleteCommandHandler {
    private final ProductDomainService productDomainService;
    private final ProductRepositoryAdapter productRepository;

    public void handle(ProductDeleteCommand command) throws CompanyDomainException {
        ProductID productId = ProductID.of(command.getProductID());
        Optional<ProductRoot> productRoot = productRepository.findById(productId);
        if (productRoot.isEmpty()) {
            throw new CompanyDomainException("Product not found by id: " + productId);
        }
        productDomainService.deleteProduct(productRoot.get());

        productRepository.save(productRoot.get());

    }
}
