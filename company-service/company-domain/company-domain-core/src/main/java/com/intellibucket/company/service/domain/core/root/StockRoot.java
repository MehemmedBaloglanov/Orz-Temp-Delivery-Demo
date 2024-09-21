package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intellibucket.company.service.domain.core.exception.StockInsufficientException;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.valueobject.StockID;
import lombok.Getter;

@Getter
public class StockRoot extends AggregateRoot<StockID> {

    private Integer stockQuantity;

    public StockRoot initialize() throws ValidateException {
        super.setId(StockID.random());
        validateStockQuantity();
        return this;
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

    public void validateStockQuantity() throws ValidateException {
        if (stockQuantity == null || stockQuantity < 0) {
            throw new ValidateException("Stock quantity cannot be null or negative");
        }
    }
}

