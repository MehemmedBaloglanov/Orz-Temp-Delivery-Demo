package com.intelliacademy.orizonroute.identity.courier;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class CourierAvailableID extends ProfileID {
    private CourierAvailableID(UUID id) {
        super(id);
    }

    public static CourierAvailableID of(UUID id) {
        return new CourierAvailableID(id);
    }

    public static CourierAvailableID of(String id) {
        return new CourierAvailableID(UUID.fromString(id));
    }

    public static CourierAvailableID random() {
        return new CourierAvailableID(UUIDv7.randomUUID());
    }
}
