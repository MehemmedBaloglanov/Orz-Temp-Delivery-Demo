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
    private String name;
    private String description;
    private Money price;
    private CompanyID companyID;
    private Integer quantity;
    private ProductStatus product;

    private ProductRoot(){
        super.setId(ProductID.random());
        this.product=ProductStatus.DRAFT;
    }
    public ProductRoot createProduct(String name, Money price, CompanyID companyID, Integer quantity) throws ValidateException {
        ProductRoot productRoot= new ProductRoot();
        productRoot.setName(productRoot.validateName(name));
        productRoot.setPrice(productRoot.validatePrice(price));
        productRoot.setQuantity(productRoot.validateQuantity(quantity));
        productRoot.setCompanyID(companyID);
        return productRoot;
    }

    private String validateName(String name) throws ValidateException {
        if (name == null || name.isBlank()) {
            throw new ValidateException("Name cannot be empty or blank");
        }
        return name;
    }

    private Money validatePrice(Money price) throws ValidateException {
        if (price == null || price.isNil()) {
            throw new ValidateException("Price cannot be null or blank");
        }
        return price;
    }

    private Integer validateQuantity(Integer quantity) throws ValidateException {
        if (quantity == null || quantity <= 0) {
            throw new ValidateException("Quantity must be greater than zero");
        }
        return quantity;
    }

}
