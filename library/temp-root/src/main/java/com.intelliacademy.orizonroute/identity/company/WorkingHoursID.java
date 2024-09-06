package com.intelliacademy.orizonroute.identity.company;

import com.intelliacademy.orizonroute.identity.ProfileID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class WorkingHoursID extends ProfileID {
    private WorkingHoursID(UUID id) {
        super(id);
    }

    public static WorkingHoursID of(UUID id) {
        return new WorkingHoursID(id);
    }

    public static WorkingHoursID of(String id) {
        return new WorkingHoursID(UUID.fromString(id));
    }

    public static WorkingHoursID random() {
        return new WorkingHoursID(UUIDv7.randomUUID());
    }
}
