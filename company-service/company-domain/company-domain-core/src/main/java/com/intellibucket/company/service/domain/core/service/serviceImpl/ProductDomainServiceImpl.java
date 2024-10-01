package com.intellibucket.company.service.domain.core.service.serviceImpl;

import com.intellibucket.company.service.domain.core.event.product.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.ProductDomainService;

import java.time.OffsetDateTime;

import static com.intellibucket.constants.DomainConstants.ZONE_ID;

public class ProductDomainServiceImpl implements ProductDomainService {

    @Override
    public ProductCreatedEvent createProduct(ProductRoot productRoot) throws ValidateException {
        productRoot.initialize();
        return new ProductCreatedEvent(productRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public ProductDeletedEvent deleteProduct(ProductRoot productRoot) throws ValidateException {
        productRoot.delete();
        return new ProductDeletedEvent(productRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public ProductActivatedEvent activateProduct(ProductRoot productRoot) throws ValidateException {
        productRoot.activate();
        return new ProductActivatedEvent(productRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public ProductOutOfStockEvent outOfStockProduct(ProductRoot productRoot) throws ValidateException {
        productRoot.outOfStock();
        return new ProductOutOfStockEvent(productRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public ProductPriceUpdatedEvent updateProduct(ProductRoot productRoot) throws ValidateException{
        return new ProductPriceUpdatedEvent(productRoot,OffsetDateTime.now(ZONE_ID));
    }


    @Override
    public ProductStockQuantityIncreasedEvent increaseProductStockQuantity(ProductRoot productRoot, Integer newQuantity) throws ValidateException {
        productRoot.increaseStockQuantity(newQuantity);
        return new ProductStockQuantityIncreasedEvent(productRoot,OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public ProductStockQuantityDecreaseEvent decreaseProductStockQuantity(ProductRoot productRoot, Integer newQuantity) throws ValidateException {
        productRoot.decreaseStockQuantity(newQuantity);
        return new ProductStockQuantityDecreaseEvent(productRoot,OffsetDateTime.now(ZONE_ID));
    }
}
