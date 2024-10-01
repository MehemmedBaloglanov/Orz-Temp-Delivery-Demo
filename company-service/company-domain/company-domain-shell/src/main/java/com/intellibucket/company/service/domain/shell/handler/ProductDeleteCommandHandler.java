package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.ProductDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.command.ProductUpdateCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Slf4j
@Component
@AllArgsConstructor
public class ProductDeleteCommandHandler {
    private final AbstractSecurityContextHolder securityContextHolder;
    private final ProductDomainService productDomainService;
    private final ProductRepositoryAdapter productRepository;

    public void handle(ProductDeleteCommand command) throws CompanyDomainException {
        ProductID productId = ProductID.of(command.getProductID());
        Optional<ProductRoot> productRoot = productRepository.findById(productId);//todo
        CompanyID companyID = this.securityContextHolder.currentCompanyID();

        if (!productRoot.isPresent()) {
            throw new CompanyDomainException("Product not found by id: " + productId);
        }

        productDomainService.deleteProduct(productRoot.get());

        productRepository.save(productRoot.get());

    }
}
