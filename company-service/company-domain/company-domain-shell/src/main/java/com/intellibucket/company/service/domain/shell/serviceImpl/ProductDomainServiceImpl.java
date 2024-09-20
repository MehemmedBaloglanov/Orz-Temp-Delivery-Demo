//package com.intellibucket.company.service.domain.shell.serviceImpl;
//
//import com.intelliacademy.orizonroute.identity.company.CompanyID;
//import com.intellibucket.company.service.domain.core.event.product.*;
//import com.intellibucket.company.service.domain.core.exception.ValidateException;
//import com.intellibucket.company.service.domain.core.root.ProductRoot;
//import com.intellibucket.company.service.domain.core.service.ProductDomainService;
//
//import java.time.OffsetDateTime;
//
//public class ProductDomainServiceImpl implements ProductDomainService {
//    @Override
//    public ProductCreatedEvent createProduct(ProductRoot productRoot, CompanyID companyID) throws ValidateException {
//        productRoot.initialize();
//        return new ProductCreatedEvent(productRoot, OffsetDateTime.now());
//    }
//
//    @Override
//    public ProductDeletedEvent deleteProduct(ProductRoot productRoot) throws ValidateException {
//        productRoot.delete();
//        return new ProductDeletedEvent(productRoot,OffsetDateTime.now());
//    }
//
//    @Override
//    public ProductActivatedEvent activateProduct(ProductRoot productRoot) throws ValidateException {
//        productRoot.activate();
//        return new ProductActivatedEvent(productRoot,OffsetDateTime.now());
//    }
//
//    @Override
//    public ProductOutOfStockEvent outOfStockProduct(ProductRoot productRoot) throws ValidateException {
//        productRoot.outOfStock();
//        return new ProductOutOfStockEvent(productRoot,OffsetDateTime.now());
//    }
//
//    @Override
//    public ProductPriceUpdatedEvent updatePriceProduct(ProductRoot productRoot) throws ValidateException {
//        return null;
//    }
//
////    @Override
////    public ProductPriceUpdatedEvent updatePriceProduct(ProductRoot productRoot) throws ValidateException {
////        productRoo;
////        return new ProductPriceUpdatedEvent(productRoot,OffsetDateTime.now());
////    }
//
//    @Override
//    public ProductQuantityUpdatedEvent updateQuantityProduct(ProductRoot productRoot) throws ValidateException {
//        productRoot.validateQuantity();
//        return new ProductQuantityUpdatedEvent(productRoot,OffsetDateTime.now());
//    }
//
//    @Override
//    public ProductStockUpdatedEvent updateStockProduct(ProductRoot productRoot) throws ValidateException {
//        return null;
//    }
//
////    @Override
////    public ProductStockUpdatedEvent updateStockProduct(ProductRoot productRoot) throws ValidateException {
////        productRoot.validateStock();
////        return new ProductStockUpdatedEvent(productRoot,OffsetDateTime.now());
////    }
//}
