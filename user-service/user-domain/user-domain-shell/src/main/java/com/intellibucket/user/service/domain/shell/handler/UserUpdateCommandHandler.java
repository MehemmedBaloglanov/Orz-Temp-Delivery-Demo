package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.request.UserDeleteCommand;
import com.intellibucket.user.service.domain.shell.dto.request.UserUpdateCommand;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUpdateCommandHandler {
    private final UserRepository userRepository;
    private final AbstractUserCommandService commandService;

    public void  handle(UserUpdateCommand command) throws UserNotFoundException {
        UserID userID = UserID.of(command.getUserId());
        Optional<UserRoot> userRoot= userRepository.findByUserId(userID);
       if (userRoot.isEmpty()) {
           throw new UserNotFoundException("User not found");
       }
       commandService.updateUser(command);
       userRepository.save(userRoot.get());

    }
}
