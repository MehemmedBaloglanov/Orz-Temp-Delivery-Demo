package com.intelliacademy.orizonroute.valueobjects.common;

import java.util.Arrays;
import java.util.List;

@ValueObject
public enum RowStatus {
    ACTIVE,
    HIDDEN,
    DELETED_BY_SYSTEM,
    DELETED_BY_USER;

    public static List<RowStatus> all() {
        return Arrays.stream(RowStatus.values()).toList();
    }

    public Boolean isActive(){
        return this.equals(ACTIVE);
    }

    public Boolean isDeletedBySystem(){
        return this.equals(DELETED_BY_SYSTEM);
    }

    public Boolean isDeletedByUser(){
        return this.equals(DELETED_BY_USER);
    }

    public Boolean isHidden(){
        return this.equals(HIDDEN);
    }

    public Boolean isDeleted(){
        return this.isDeletedBySystem() || this.isDeletedByUser();
    }
}
