package com.intellibucket.user.service.domain.core.event;

import com.intellibucket.event.DomainEvent;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
@RequiredArgsConstructor
@Getter
public class UserDomainEvent implements DomainEvent<UserRoot> {
        private final UserRoot userRoot;
        private final OffsetDateTime createdAt;
    }

