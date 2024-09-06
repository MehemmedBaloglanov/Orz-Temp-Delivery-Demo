package com.intelliacademy.orizonroute.identity.customer;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class PromoCodeRelationID extends ProfileID {
    private PromoCodeRelationID(UUID id) {
        super(id);
    }

    public static PromoCodeRelationID of(UUID id) {
        return new PromoCodeRelationID(id);
    }

    public static PromoCodeRelationID of(String id) {
        return new PromoCodeRelationID(UUID.fromString(id));
    }

    public static PromoCodeRelationID random() {
        return new PromoCodeRelationID(UUIDv7.randomUUID());
    }
}
