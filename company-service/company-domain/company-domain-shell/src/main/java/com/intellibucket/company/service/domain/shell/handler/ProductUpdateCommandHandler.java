package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.ProductUpdateCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.ProductResponse;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductUpdateCommandHandler {
    private final AbstractSecurityContextHolder securityContextHolder;
    private final ProductDomainService productDomainService;
    private final ProductRepositoryAdapter productRepository;

    public void handle(ProductUpdateCommand command) throws CompanyDomainException {
        ProductID productId = ProductID.of(command.getProductId());
        Optional<ProductRoot> productRoot = productRepository.findById(productId);//todo
        CompanyID companyID = this.securityContextHolder.currentCompanyID();

        if (!productRoot.isPresent()) {
            throw new CompanyDomainException("Product not found by id: " + productId);
        }

        productDomainService.updateProduct(productRoot.get());

        productRepository.save(productRoot.get());

    }
}
