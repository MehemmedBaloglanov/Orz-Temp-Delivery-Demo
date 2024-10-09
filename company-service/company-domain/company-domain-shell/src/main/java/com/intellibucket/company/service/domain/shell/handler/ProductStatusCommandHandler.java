package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.event.product.ProductActivatedEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductStatusCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.company.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ProductStatusCommandHandler {
    private final AbstractSecurityContextHolder securityContextHolder;
    private final ProductDomainService productDomainService;
    private final ProductRepositoryAdapter productRepository;
    public void handle(ProductStatusCommand command) throws CompanyDomainException {
        ProductID productId = ProductID.of(command.getProductId());
        Optional<ProductRoot> productRootOptional = productRepository.findById(productId);
        CompanyID companyID = this.securityContextHolder.currentCompanyID();

        if (productRootOptional.isEmpty()) {
            throw new CompanyDomainException("Product not found by id: " + productId);
        }

        ProductActivatedEvent productActivatedEvent = productDomainService.activateProduct(productRootOptional.get());

        productRepository.save(productRootOptional.get());

    }
}
