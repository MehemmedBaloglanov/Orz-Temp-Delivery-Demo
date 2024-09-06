package com.intelliacademy.orizonroute.identity.promotion;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class PromotionID extends ProfileID {
    private PromotionID(UUID id) {
        super(id);
    }

    public static PromotionID of(UUID id) {
        return new PromotionID(id);
    }

    public static PromotionID of(String id) {
        return new PromotionID(UUID.fromString(id));
    }

    public static PromotionID random() {
        return new PromotionID(UUIDv7.randomUUID());
    }
}
