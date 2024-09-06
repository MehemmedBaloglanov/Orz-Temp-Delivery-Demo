package com.intelliacademy.orizonroute.identity.courier;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class SchedulerAvailableActivationID extends BaseID<UUID> {
    private SchedulerAvailableActivationID(UUID id) {
        super(id);
    }

    public static SchedulerAvailableActivationID of(UUID id) {
        return new SchedulerAvailableActivationID(id);
    }

    public static SchedulerAvailableActivationID of(String id) {
        return new SchedulerAvailableActivationID(UUID.fromString(id));
    }

    public static SchedulerAvailableActivationID random() {
        return new SchedulerAvailableActivationID(UUIDv7.randomUUID());
    }
}
