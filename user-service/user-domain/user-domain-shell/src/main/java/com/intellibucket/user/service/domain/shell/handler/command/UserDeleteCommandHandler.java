package com.intellibucket.user.service.domain.shell.handler.command;

import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDeleteCommandHandler {
    private final UserRepository userRepository;
//    private final UserDomainServiceImpl userDomainService;
//
//    public void handle(UserDeleteCommand command) throws UserDomainException {
//        UserID userID = securityContextHolder.currentUserID();
//        Optional<UserRoot> userRoot = userRepository.findByUserId(userID);
//
//        if (userRoot.isEmpty()) {
//            throw new UserNotFoundException("User not found with id: " + userID.value());
//        }
//
//        UserDeletedDomainEvent userDeletedDomainEvent = userDomainService.userDeleted(userRoot.get());
//
//        userRepository.delete(userRoot.get());
//    }
}