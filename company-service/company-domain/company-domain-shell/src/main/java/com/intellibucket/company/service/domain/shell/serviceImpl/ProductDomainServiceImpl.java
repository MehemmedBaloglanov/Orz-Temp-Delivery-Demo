package com.intellibucket.company.service.domain.shell.serviceImpl;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.product.*;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;

public class ProductDomainServiceImpl implements ProductDomainService {
    @Override
    public ProductCreatedEvent createProduct(ProductRoot productRoot, CompanyID companyID) {
        return null;
    }

    @Override
    public ProductDeletedEvent deleteProduct(ProductRoot productRoot) {
        return null;
    }

    @Override
    public ProductActivatedEvent activateProduct(ProductRoot productRoot) {
        return null;
    }

    @Override
    public ProductOutOfStockEvent outOfStockProduct(ProductRoot productRoot) {
        return null;
    }

    @Override
    public ProductPriceUpdatedEvent updatePriceProduct(ProductRoot productRoot) {
        return null;
    }

    @Override
    public ProductQuantityUpdatedEvent updateQuantityProduct(ProductRoot productRoot) {
        return null;
    }

    @Override
    public ProductStockUpdatedEvent updateStockProduct(ProductRoot productRoot) {
        return null;
    }
}
