package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CommentID;
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
    private String name;
    private String description;
    private Money price;
    private CompanyID companyID;
    private Integer quantity;
    private StockRoot stock;
    private ProductStatus status;


    public ProductRoot initialize() throws ValidateException {
        super.setId(ProductID.random());
        validateDraft();
        status = ProductStatus.DRAFT;
        validateProduct();
        return this;
    }

    private void validateProduct() throws ValidateException {
        validateName();
        validatePrice();
        validateQuantity();
        validateCompanyID();
        validateStock();
    }

    public void validateName() throws ValidateException {
        if (name == null || name.isBlank()) {
            throw new ValidateException("Name cannot be empty or blank");
        }
    }

    public void validatePrice() throws ValidateException {
        if (price == null || price.isNil()) {
            throw new ValidateException("Price cannot be null or blank");
        }
    }

    public void validateQuantity() throws ValidateException {
        if (quantity == null || quantity <= 0) {
            throw new ValidateException("Quantity must be greater than zero");
        }
    }

    public void validateCompanyID() throws ValidateException {
        if(companyID==null){
            throw new ValidateException("CompanyID cannot be null");
        }
    }

    public void validateStock() throws ValidateException {
        if(stock==null){
            throw new ValidateException("Stock cannot be null");
        }
    }

    public void validateDraft() throws ValidateException {
        if(status.isDraft() == null){
            throw new ValidateException("Draft cannot be null");
        }
    }

    public ProductRoot active() throws ValidateException {
        if (status.isActive()) {
            throw new ValidateException("The product is already in ACTIVE status.");
        }
        this.status = ProductStatus.ACTIVE;
        return this;
    }

    public ProductRoot deleted() throws ValidateException {
        if(status.isDeleted()){
            throw new ValidateException("This product is already deleted");
        }
        this.status=ProductStatus.DELETED;
        return this;
    }

    public ProductRoot outOfStock() throws ValidateException {
        if (status.isOutOfStock()) {
            throw new ValidateException("The product is already in OUT_OF_STOCK status.");
        }
        this.status = ProductStatus.OUT_OF_STOCK;
        return this;
    }
}
