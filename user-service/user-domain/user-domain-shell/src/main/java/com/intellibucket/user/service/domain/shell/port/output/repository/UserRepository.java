package com.intellibucket.user.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.util.Optional;

public interface UserRepository {
    Optional<UserRoot> findByUserId(UserID userId);

    Optional<UserRoot> findByEmail(Email email);

    UserRoot save(UserRoot userRoot);
}
//    UserRoot update(UserRoot userRoot) throws UserNotFoundException;
//
//    UserRoot delete(UserRoot userRoot) throws UserNotFoundException;
//
