package com.intellibucket.user.service.domain.shell.handler;

import com.intellibucket.user.service.domain.shell.dto.request.UserLoginCommand;
import com.intellibucket.user.service.domain.shell.dto.response.UserLoginResponse;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserLoginCommandHandler {
private final UserRepository userRepository;
    public UserLoginResponse handle(UserLoginCommand command) {
        return null;
    }
}
