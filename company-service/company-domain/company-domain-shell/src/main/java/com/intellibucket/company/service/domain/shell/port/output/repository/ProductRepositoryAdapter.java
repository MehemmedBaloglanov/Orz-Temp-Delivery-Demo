package com.intellibucket.company.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

public interface ProductRepositoryAdapter {
    ProductRoot save(ProductRoot productRoot);
    ProductRoot findById(ProductID productId);
    void deleteById(ProductRoot productRoot);

}
