package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intellibucket.company.service.domain.core.exception.StockInsufficientException;
import com.intellibucket.company.service.domain.core.exception.ValidateException;

public class StockRoot extends AggregateRoot<ProductID> {
    private Integer stockQuantity;

    public StockRoot(ProductID productID, Integer stockQuantity) {
         super.setId(ProductID.random());
        this.stockQuantity = stockQuantity;
    }

    // TODO: 9/12/2024 Məhəmməd
    public static StockRoot initializeStock(ProductID productID,Integer stockQuantity) throws ValidateException {
        return new StockRoot(productID,validateStockQuantity(stockQuantity));
    }

    public void addStock(Integer quantityToAdd) throws ValidateException {
        if (quantityToAdd == null || quantityToAdd <= 0) {
            throw new ValidateException("Quantity to add must be positive and not null");
        }
        this.stockQuantity += quantityToAdd;
    }


    public void removeStock(Integer quantityToRemove) throws ValidateException, StockInsufficientException {
        if (quantityToRemove == null || quantityToRemove <= 0) {
            throw new ValidateException("Quantity to remove must be positive and not null");
        }
        if (quantityToRemove > this.stockQuantity) {
            throw new StockInsufficientException("Insufficient stock to remove that quantity");
        }
        this.stockQuantity -= quantityToRemove;
    }

    public static Integer validateStockQuantity(Integer stockQuantity) throws ValidateException {
        if(stockQuantity == null || stockQuantity<0){
            throw new ValidateException("Stock quantity cannot be null or negative");
        }
        return stockQuantity;
    }


}

