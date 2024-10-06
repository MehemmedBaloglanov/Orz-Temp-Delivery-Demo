package com.intellibucket.user.service.domain.core.event;

import com.intellibucket.domain.event.DomainEvent;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
public class UserDomainEvent implements DomainEvent<UserRoot> {
    private final UserRoot userRoot;
    private final OffsetDateTime createdAt;
}