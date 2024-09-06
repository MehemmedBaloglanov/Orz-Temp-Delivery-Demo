package com.intelliacademy.orizonroute.identity.customer;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CustomerSettingID extends ProfileID {
    private CustomerSettingID(UUID id) {
        super(id);
    }

    public static CustomerSettingID of(UUID id) {
        return new CustomerSettingID(id);
    }

    public static CustomerSettingID of(String id) {
        return new CustomerSettingID(UUID.fromString(id));
    }

    public static CustomerSettingID random() {
        return new CustomerSettingID(UUIDv7.randomUUID());
    }
}
