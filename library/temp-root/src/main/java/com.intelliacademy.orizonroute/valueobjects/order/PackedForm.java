package com.intelliacademy.orizonroute.valueobjects.order;

import com.intelliacademy.orizonroute.valueobjects.common.ValueObject;

import java.util.List;

@ValueObject
public enum PackedForm {
    CARTON,
    GIFT,
    BAG,
    OTHER,
    NONE;

    public static List<PackedForm> all() {
        return List.of(CARTON, GIFT, BAG, OTHER);
    }
}
