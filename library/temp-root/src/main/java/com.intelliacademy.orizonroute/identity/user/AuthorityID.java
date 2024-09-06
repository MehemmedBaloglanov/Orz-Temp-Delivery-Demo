package com.intelliacademy.orizonroute.identity.user;

import com.intelliacademy.orizonroute.root.BaseID;
import com.intelliacademy.orizonroute.util.UUIDv7;

import java.util.UUID;

public class AuthorityID extends BaseID<UUID> {
    private AuthorityID(UUID id) {
        super(id);
    }

    public static AuthorityID of(UUID id) {
        return new AuthorityID(id);
    }

    public static AuthorityID of(String id) {
        return new AuthorityID(UUID.fromString(id));
    }

    public static AuthorityID random() {
        return new AuthorityID(UUIDv7.randomUUID());
    }
}
