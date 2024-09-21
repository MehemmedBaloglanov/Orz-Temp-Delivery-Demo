package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.UserLoginCommand;
import com.intellibucket.user.service.domain.shell.dto.response.UserLoginResponse;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserLoginCommandHandler {
private final UserRepository userRepository;
    private final AbstractUserCommandService commandService;

    public UserLoginResponse handle(UserID userId)throws UserDomainException {
        Optional<UserRoot> userRootOptional = userRepository.findByUserId(userId);
        if (userRootOptional.isEmpty()) {
            throw new UserNotFoundException(" user not found ");
        }
return null;
    }
}
