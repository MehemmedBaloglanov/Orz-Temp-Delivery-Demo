package com.intellibucket.company.service.domain.core.valueobject;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

@ValueObject
public enum ProductStatus {
    DRAFT,
    ACTIVE,
    DELETED,
    OUT_OF_STOCK;


    public Boolean isDraft(){
        return this == DRAFT;
    }

    public Boolean isActive(){
        return this==ACTIVE;
    }

    public Boolean isDeleted(){
        return this==DELETED;
    }

    public Boolean isOutOfStock(){
        return this==OUT_OF_STOCK;
    }
}
