package com.intellibucket.user.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.event.UserChangePasswordDomainEvent;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.port.UserDomainService;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.shell.dto.request.UserChangePasswordCommand;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class UserChangePasswordCommandHandler {
    private final UserRepository userRepository;
    private final UserDomainService userDomainService;

    public void handle(UserChangePasswordCommand command) throws UserDomainException {
//        UserID userID = UserID.random(); // FIXME userID securityContextHolder'dan alinacaq
//        // id= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        Optional<UserRoot> byUserId = userRepository.findByUserId(userID);
//
//        if (byUserId.isEmpty()) {
//            throw new UserNotFoundException("user not found with id:" + userID);
//        }
//        UserRoot user = byUserId.get();
//
//        if (!user.getPassword().isEqual(Password.builder().value(command.getOldPassword()).build())) {
//            throw new PasswordValidationException("old password is invalid!");
//        }
//
//        Password newPassword = Password.builder().value(command.getNewPassword()).build();
//        //  user.userChangePassword(newPassword);
//        UserChangePasswordDomainEvent userChangePasswordDomainEvent = userDomainService.userChangePassword(user);
//
//        UserRoot savedUserRoot = userRepository.save(byUserId.get());
//        if (savedUserRoot == null) {
//            throw new UserSavedException("User could not be saved: " + user.getUserID());
//        }
    }
}