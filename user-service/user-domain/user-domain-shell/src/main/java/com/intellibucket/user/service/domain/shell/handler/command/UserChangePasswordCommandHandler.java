package com.intellibucket.user.service.domain.shell.handler.command;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.UserChangePasswordCommand;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserChangePasswordCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void handle(UserChangePasswordCommand command) throws UserDomainException {
    }
}