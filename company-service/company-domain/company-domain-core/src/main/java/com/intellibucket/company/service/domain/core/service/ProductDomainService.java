package com.intellibucket.company.service.domain.core.service;

import com.intellibucket.company.service.domain.core.event.product.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

public interface ProductDomainService {

    ProductCreatedEvent createProduct(ProductRoot productRoot) throws ValidateException;

    ProductDeletedEvent deleteProduct(ProductRoot productRoot) throws ValidateException;

    ProductActivatedEvent activateProduct(ProductRoot productRoot) throws ValidateException;

    //todo
    ProductOutOfStockEvent outOfStockProduct(ProductRoot productRoot) throws ValidateException;

    ProductPriceUpdatedEvent updateProduct(ProductRoot productRoot) throws ValidateException;

    //todo
    ProductStockQuantityIncreasedEvent increaseProductStockQuantity(ProductRoot productRoot,Integer newQuantity) throws ValidateException;

    //todo
    ProductStockQuantityDecreaseEvent decreaseProductStockQuantity(ProductRoot productRoot,Integer newQuantity) throws ValidateException;



}
