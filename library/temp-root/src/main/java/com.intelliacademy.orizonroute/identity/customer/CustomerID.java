package com.intelliacademy.orizonroute.identity.customer;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CustomerID extends ProfileID {
    private CustomerID(UUID id) {
        super(id);
    }

    public static CustomerID of(UUID id) {
        return new CustomerID(id);
    }

    public static CustomerID of(String id) {
        return new CustomerID(UUID.fromString(id));
    }

    public static CustomerID random() {
        return new CustomerID(UUIDv7.randomUUID());
    }
}
