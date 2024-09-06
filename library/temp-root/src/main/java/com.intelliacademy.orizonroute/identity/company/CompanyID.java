package com.intelliacademy.orizonroute.identity.company;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CompanyID extends ProfileID {
    private CompanyID(UUID id) {
        super(id);
    }

    public static CompanyID of(UUID id) {
        return new CompanyID(id);
    }

    public static CompanyID of(String id) {
        return new CompanyID(UUID.fromString(id));
    }

    public static CompanyID random() {
        return new CompanyID(UUIDv7.randomUUID());
    }
}
