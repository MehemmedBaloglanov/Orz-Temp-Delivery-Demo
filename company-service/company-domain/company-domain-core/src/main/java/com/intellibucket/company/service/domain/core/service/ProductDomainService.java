package com.intellibucket.company.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.product.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

public interface ProductDomainService {

    ProductCreatedEvent createProduct(ProductRoot productRoot, CompanyID companyID) throws ValidateException;

    ProductDeletedEvent deleteProduct(ProductRoot productRoot) throws ValidateException;

    ProductActivatedEvent activateProduct(ProductRoot productRoot) throws ValidateException;

    ProductOutOfStockEvent  outOfStockProduct(ProductRoot productRoot) throws ValidateException;

    ProductPriceUpdatedEvent updatePriceProduct(ProductRoot productRoot) throws ValidateException;

    ProductQuantityUpdatedEvent updateQuantityProduct(ProductRoot productRoot) throws ValidateException;

    ProductStockUpdatedEvent updateStockProduct(ProductRoot productRoot) throws ValidateException;

}
