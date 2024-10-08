package com.intellibucket.company.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

import java.util.Optional;

public interface ProductRepositoryAdapter {
    ProductRoot save(ProductRoot productRoot);
    Optional<ProductRoot> findById(ProductID productId);
    void deleteById(ProductID productID) throws CompanyDomainException;

}
