package com.intellibucket.user.service.domain.core.event;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.time.OffsetDateTime;

public class UserRegisteredEvent extends UserDomainEvent {
    public UserRegisteredEvent(UserRoot userRoot, OffsetDateTime createdAt) {super(userRoot, createdAt);}
}
