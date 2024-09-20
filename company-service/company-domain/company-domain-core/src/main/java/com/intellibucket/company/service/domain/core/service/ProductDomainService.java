package com.intellibucket.company.service.domain.core.service;

import com.intellibucket.company.service.domain.core.event.product.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

public interface ProductDomainService {

    ProductCreatedEvent createProduct(ProductRoot productRoot) throws ValidateException;

    ProductDeletedEvent deleteProduct(ProductRoot productRoot) throws ValidateException;

    ProductActivatedEvent activateProduct(ProductRoot productRoot) throws ValidateException;

    ProductOutOfStockEvent outOfStockProduct(ProductRoot productRoot) throws ValidateException;

    ProductPriceUpdatedEvent updateProductPrice(ProductRoot productRoot) throws ValidateException;

    ProductQuantityIncreasedEvent increaseProductQuantity(ProductRoot productRoot) throws ValidateException;



}
