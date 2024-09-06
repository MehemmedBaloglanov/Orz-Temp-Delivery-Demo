package com.intelliacademy.orizonroute.identity.customer;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CustomerAddressID extends ProfileID {
    private CustomerAddressID(UUID id) {
        super(id);
    }

    public static CustomerAddressID of(UUID id) {
        return new CustomerAddressID(id);
    }

    public static CustomerAddressID of(String id) {
        return new CustomerAddressID(UUID.fromString(id));
    }

    public static CustomerAddressID random() {
        return new CustomerAddressID(UUIDv7.randomUUID());
    }
}
