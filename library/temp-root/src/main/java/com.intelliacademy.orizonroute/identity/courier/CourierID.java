package com.intelliacademy.orizonroute.identity.courier;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CourierID extends ProfileID {
    private CourierID(UUID id) {
        super(id);
    }

    public static CourierID of(UUID id) {
        return new CourierID(id);
    }

    public static CourierID of(String id) {
        return new CourierID(UUID.fromString(id));
    }

    public static CourierID random() {
        return new CourierID(UUIDv7.randomUUID());
    }
}
