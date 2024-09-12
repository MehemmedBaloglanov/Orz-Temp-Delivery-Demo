package com.intellibucket.user.service.domain.core.event;

import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.time.OffsetDateTime;

public class UserUpdatedDomainEvent extends UserDomainEvent {
    public UserUpdatedDomainEvent(UserRoot userRoot, OffsetDateTime createdAt){super (userRoot, createdAt);}
}
