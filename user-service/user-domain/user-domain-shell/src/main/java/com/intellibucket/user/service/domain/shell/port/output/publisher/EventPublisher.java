package com.intellibucket.user.service.domain.shell.port.output.publisher;

import com.intellibucket.user.service.domain.core.event.*;

public interface EventPublisher {
    void publishUserChangePasswordEvent(UserChangePasswordDomainEvent event);

    void publishUserDeletedEvent(UserDeletedDomainEvent event);

    void publishUserLoggedInEvent(UserLoggedInDomainEvent event);

    void publishUserRegisteredEvent(UserRegisteredEvent event);

    void publishUserUpdatedEvent(UserUpdatedDomainEvent event);
}