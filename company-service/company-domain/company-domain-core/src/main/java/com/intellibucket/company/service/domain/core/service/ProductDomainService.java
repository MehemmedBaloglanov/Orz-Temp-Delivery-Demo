package com.intellibucket.company.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.product.*;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

public interface ProductDomainService {

    ProductCreatedEvent createProduct(ProductRoot productRoot, CompanyID companyID);

    ProductDeletedEvent deleteProduct(ProductRoot productRoot);

    ProductActivatedEvent activateProduct(ProductRoot productRoot);

    ProductOutOfStockEvent  outOfStockProduct(ProductRoot productRoot);

    ProductPriceUpdatedEvent updatePriceProduct(ProductRoot productRoot);

    ProductQuantityUpdatedEvent updateQuantityProduct(ProductRoot productRoot);

    ProductStockUpdatedEvent updateStockProduct(ProductRoot productRoot);

}
