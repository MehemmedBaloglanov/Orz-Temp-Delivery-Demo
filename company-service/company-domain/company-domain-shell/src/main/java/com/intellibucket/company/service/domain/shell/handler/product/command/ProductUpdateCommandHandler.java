package com.intellibucket.company.service.domain.shell.handler.product.command;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.event.product.ProductPriceUpdatedEvent;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.command.product.ProductUpdateCommand;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import com.intellibucket.company.service.domain.shell.security.AbstractSecurityContextHolder;
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
        log.info("ProductUpdateCommandHandler");
        ProductID productId = ProductID.of(command.getProductId());
        Optional<ProductRoot> productRootOptional = productRepository.findById(productId);

        if (productRootOptional.isEmpty()) {
            throw new CompanyDomainException("Product not found by id: " + productId);
        }

        ProductRoot productRoot = productRootOptional.get();
        productRoot.updateName(command.getProductName());
        productRoot.updateStockQuantity(Integer.valueOf(command.getStockQuantity()));
        Money money = Money.of(command.getPrice());
        productRoot.updatePrice(money);

        ProductPriceUpdatedEvent productPriceUpdatedEvent = productDomainService.updateProduct(productRoot);

        productRepository.save(productRoot);
        log.info("Product updated: {}", productRoot);

    }
}
