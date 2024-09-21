package com.intellibucket.user.service.domain.core.event;

import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.time.OffsetDateTime;

public class UserDeletedDomainEvent extends UserDomainEvent {
    public UserDeletedDomainEvent(UserRoot userRoot, OffsetDateTime createdAt) {
        super(userRoot, createdAt);
    }

}


