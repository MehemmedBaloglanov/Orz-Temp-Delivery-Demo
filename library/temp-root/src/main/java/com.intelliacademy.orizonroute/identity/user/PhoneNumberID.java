package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class PhoneNumberID extends BaseID<UUID> {
    private PhoneNumberID(UUID id) {
        super(id);
    }

    public static PhoneNumberID of(UUID id) {
        return new PhoneNumberID(id);
    }

    public static PhoneNumberID of(String id) {
        return new PhoneNumberID(UUID.fromString(id));
    }

    public static PhoneNumberID random() {
        return new PhoneNumberID(UUIDv7.randomUUID());
    }
}
