package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ProductRoot extends AggregateRoot<ProductID> {
    private String name;
    private Money price;
    private CompanyID companyID;
    private Integer stockQuantity;
    private ProductStatus status;

    public ProductRoot initialize() throws ValidateException {
        validateProduct();
        super.setId(ProductID.random());
        status = ProductStatus.DRAFT;
        return this;
    }


    //----------------------------------->VALIDATE FIELDS

    private void validateProduct() throws ValidateException {
        validateName();
        validatePrice();
        validateStockQuantity();
        //todo
//        validateCompanyID();
    }

    private void validateName() throws ValidateException {
        if (name == null || name.isEmpty()) {
            throw new ValidateException("Name cannot be empty or blank.");
        }
    }

    private void validatePrice() throws ValidateException {
        System.out.println("validatePrice method called");
        if (this.price.getAmount() == null) {
            throw new ValidateException("Price cannot be null or empty.");
        }
    }

    //todo
//    private void validateCompanyID() throws ValidateException {
//        if (companyID == null) {
//            throw new ValidateException("CompanyID cannot be null.");
//        }
//    }

    private void validateStockQuantity() throws ValidateException {
        if (stockQuantity == null || stockQuantity < 0) {
            throw new ValidateException("StockQuantity cannot be null or negative.");
        }
    }


    //----------------------------------->CHANGE PRODUCT STATUS

    public ProductRoot activate() throws ValidateException {
        if (status.isActive()) {
            throw new ValidateException("The product is already in ACTIVE status.");
        }
        this.status = ProductStatus.ACTIVE;
        return this;
    }

    public ProductRoot delete() throws ValidateException {
        if (status.isDeleted()) {
            throw new ValidateException("The product is already deleted.");
        }
        this.status = ProductStatus.DELETED;
        return this;
    }

    public ProductRoot outOfStock() throws ValidateException {
        if (status.isOutOfStock()) {
            throw new ValidateException("The product is already OUT_OF_STOCK.");
        }
        this.status = ProductStatus.OUT_OF_STOCK;
        return this;
    }


    //----------------------------->DECREASE AND INCREASE METHODS


    public ProductRoot decreaseStockQuantity(Integer amount) throws ValidateException {
        if (amount == null || amount <= 0) {
            throw new ValidateException("Decrease amount must be greater than zero.");
        }
        if (this.stockQuantity - amount < 0) {
            throw new ValidateException("Stock quantity cannot be less than zero.");
        }
        this.stockQuantity -= amount;
        return this;
    }

    public ProductRoot increaseStockQuantity(Integer amount) throws ValidateException {
        if (amount == null || amount <= 0) {
            throw new ValidateException("Increase stock quantity must be greater than zero.");
        }
        if (this.stockQuantity + amount < 0) {
            throw new ValidateException("StockQuantity cannot be less than zero.");
        }
        this.stockQuantity += amount;
        return this;
    }


    //-----------------------------> UPDATE PRODUCT FIELDS
    public ProductRoot updateName(String name) throws ValidateException {
        validateName();
        this.name=name;
        return this;
    }

    public ProductRoot updatePrice(Money price) throws ValidateException {
        validatePrice();
        this.price = price;
        return this;
    }

    public ProductRoot updateStockQuantity(Integer stockQuantity) throws ValidateException {
        validateStockQuantity();
        this.stockQuantity = stockQuantity;
        return this;
    }
}
