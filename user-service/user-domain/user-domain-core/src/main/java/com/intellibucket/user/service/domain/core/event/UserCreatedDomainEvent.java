package com.intellibucket.user.service.domain.core.event;

import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.time.OffsetDateTime;

public class UserCreatedDomainEvent extends UserDomainEvent {
    public UserCreatedDomainEvent(UserRoot userRoot, OffsetDateTime createdAt) {
        super(userRoot, createdAt);
    }

}
