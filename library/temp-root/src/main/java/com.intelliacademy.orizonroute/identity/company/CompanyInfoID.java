package com.intelliacademy.orizonroute.identity.company;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CompanyInfoID extends ProfileID {
    private CompanyInfoID(UUID id) {
        super(id);
    }

    public static CompanyInfoID of(UUID id) {
        return new CompanyInfoID(id);
    }

    public static CompanyInfoID of(String id) {
        return new CompanyInfoID(UUID.fromString(id));
    }

    public static CompanyInfoID random() {
        return new CompanyInfoID(UUIDv7.randomUUID());
    }
}
