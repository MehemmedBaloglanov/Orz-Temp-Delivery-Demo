package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.request.CustomerUpdateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerUpdateCommandHandler {
    private final UserRepository userRepository;
    private final AbstractUserCommandService commandService;
    private final UserDomainService userDomainService;

    public void handle(CustomerUpdateCommand command) throws UserNotFoundException {
        UserRoot userUpdate = UserCommandMapper.customerUpdateCommandToUserRoot(command);

        UserID userID = UserID.of(command.getUserId());
        Optional<UserRoot> userRoot= userRepository.findByUserId(userID);
       if (userRoot.isEmpty()) {
           throw new UserNotFoundException("User not found with ID" + userID.value());
       }
        userDomainService.userUpdated(userUpdate);
        commandService.updateCustomer(command);
        userRepository.update(userUpdate);
        userRepository.save(userRoot.get());

    }
}
