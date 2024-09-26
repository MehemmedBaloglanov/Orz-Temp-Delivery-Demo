package com.intellibucket.user.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.UserUpdatedDomainEvent;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.shell.dto.command.abstracts.AbstractUserCreateCommand;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyUpdateCommand;
import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CompanyUpdateCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void handle(CompanyUpdateCommand command) throws UserNotFoundException {
        UserRoot userUpdate = UserCommandMapper.companyUpdateCommandToUserRoot(command);
        Optional<AbstractUserCreateCommand> userOptional = userRepository.findByEmail(userFromCommand.getEmail());
        UserID companyName = UserID.of(command.getCompanyName());
        Optional<UserRoot> userRoot = userRepository.findByUserId(companyName);
        if (userRoot.isEmpty()) {
            throw new UserNotFoundException("User not found with ID" + companyName.value());
        }
        UserUpdatedDomainEvent userUpdatedDomainEvent = userDomainService.userUpdated(userUpdate);
        userRepository.update(userUpdate);
        userRepository.save(userRoot.get());
    }
}
