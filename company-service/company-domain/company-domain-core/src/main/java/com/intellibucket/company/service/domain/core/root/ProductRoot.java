package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;


public class ProductRoot extends AggregateRoot<ProductID> {
    private String name;
    private String description;
    private Money price;
    private CompanyID companyID;
    private Integer quantity;
    private ProductStatus products;




}
