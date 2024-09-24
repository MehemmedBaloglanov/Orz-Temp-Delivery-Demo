package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductRoot extends AggregateRoot<ProductID> {

    private ProductID productID;
//    private String name;
//    private String description;
    private Money price;
    private CompanyID companyID;
    private Integer quantity;
    private Integer stockQuantity;
    private ProductStatus status;

    public ProductRoot initialize() throws ValidateException {
        super.setId(ProductID.random());
        if (status == null) {
            status = ProductStatus.DRAFT;  // İlkin status DRAFT olaraq təyin edilir
        }
        validateProduct();
        return this;
    }



    //----------------------------------->VALIDATE FIELDS

    private void validateProduct() throws ValidateException {
//        validateName();
//        validateDescription();
        validatePrice();
        validateQuantity();
        validateStockQuantity();
        validateCompanyID();
    }

//    private void validateName() throws ValidateException {
//        if (name == null || name.isEmpty()) {
//            throw new ValidateException("Name cannot be empty or blank.");
//        }
//    }
//
//    private void validateDescription() throws ValidateException {
//        if (description == null || description.isBlank()) {
//            throw new ValidateException("Description cannot be empty or blank.");
//        }
//    }

    private void validatePrice() throws ValidateException {
        if (price == null || price.isNil()) {
            throw new ValidateException("Price cannot be null or zero.");
        }
    }

    private void validateQuantity() throws ValidateException {
        if (quantity == null || quantity < 0) {
            throw new ValidateException("Quantity must be greater than zero.");
        }
    }

    private void validateCompanyID() throws ValidateException {
        if (companyID == null) {
            throw new ValidateException("CompanyID cannot be null.");
        }
    }

    private void validateStockQuantity() throws ValidateException {
        if (stockQuantity == null || stockQuantity < 0) {
            throw new ValidateException("StockQuantity cannot be null or negative.");
        }
    }


    //----------------------------------->UPDATE PRODUCT STATUS

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

    //--------------------------------->UPDATE OTHER FIELDS

//    public ProductRoot updateName(String newName) throws ValidateException {
//        if (newName == null || newName.isBlank()) {
//            throw new ValidateException("New name cannot be empty or blank.");
//        }
//        this.name = newName;
//        return this;
//    }
//
//    public ProductRoot updateDescription(String newDescription) throws ValidateException {
//        if (newDescription == null || newDescription.isBlank()) {
//            throw new ValidateException("New description cannot be empty or blank.");
//        }
//        this.description = newDescription;
//        return this;
//    }

    //todo: isNil methodu ile yoxlamaq duzgundurmu?
    public ProductRoot updatePrice(Money newPrice) throws ValidateException {
        if (newPrice == null || newPrice.isNil()) {
            throw new ValidateException("New price cannot be null or zero.");
        }
        this.price = newPrice;
        return this;
    }

    public ProductRoot updateQuantity(Integer newQuantity) throws ValidateException {
        if (newQuantity == null || newQuantity <= 0) {
            throw new ValidateException("New quantity must be greater than zero.");
        }
        this.quantity = newQuantity;
        return this;
    }

    public ProductRoot updateStockQuantity(Integer newStockQuantity) throws ValidateException {
        if (newStockQuantity == null || newStockQuantity < 0) {
            throw new ValidateException("New stock quantity cannot be null or negative.");
        }
        this.stockQuantity = newStockQuantity;
        return this;
    }

    //----------------------------->DECREASE AND INCREASE METHODS

    public ProductRoot increaseQuantity(Integer amount) throws ValidateException {
        if (amount == null || amount <= 0) {
            throw new ValidateException("Increase amount must be greater than zero.");
        }
        this.quantity += amount;
        return this;
    }

    public ProductRoot decreaseQuantity(Integer amount) throws ValidateException {
        if (amount == null || amount <= 0) {
            throw new ValidateException("Decrease amount must be greater than zero.");
        }
        if (this.quantity - amount < 0) {
            throw new ValidateException("Quantity cannot be less than zero.");
        }
        this.quantity -= amount;
        return this;
    }
}
