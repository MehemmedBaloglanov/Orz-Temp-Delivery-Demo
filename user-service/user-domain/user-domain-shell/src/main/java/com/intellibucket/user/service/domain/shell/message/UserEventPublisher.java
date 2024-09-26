package com.intellibucket.user.service.domain.shell.message;

import com.intellibucket.user.service.domain.core.event.UserRegisteredEvent;

public interface UserEventPublisher {
    void publish(UserRegisteredEvent event);
}