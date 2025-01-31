package com.intellibucket.user.service.domain.core.event;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@ToString
public class UserChangePasswordDomainEvent extends UserDomainEvent {

    public UserChangePasswordDomainEvent(UserRoot userRoot, OffsetDateTime createdAt) {
        super(userRoot, createdAt);
    }
}

