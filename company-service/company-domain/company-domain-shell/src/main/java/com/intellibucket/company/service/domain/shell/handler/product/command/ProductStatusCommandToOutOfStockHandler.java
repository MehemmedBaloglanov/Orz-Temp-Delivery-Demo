package com.intellibucket.company.service.domain.shell.handler.product.command;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.event.product.ProductOutOfStockEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductStatusCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductStatusOutOfStockCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.company.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ProductStatusCommandToOutOfStockHandler {
    private final AbstractSecurityContextHolder securityContextHolder;
    private final ProductDomainService productDomainService;
    private final ProductRepositoryAdapter productRepository;

    public void handle(ProductStatusOutOfStockCommand command) throws CompanyDomainException {
        log.info("Product out of stock handler");
        ProductID productId = ProductID.of(command.getProductId());
        Optional<ProductRoot> productRootOptional = productRepository.findById(productId);

        if (productRootOptional.isEmpty()) {
            throw new CompanyDomainException("Product not found by id: " + productId);
        }
        ProductOutOfStockEvent productActivatedEvent = productDomainService.outOfStockProduct(productRootOptional.get());
        productRepository.save(productRootOptional.get());
        log.info("Product status change to out of stock");

    }
}
