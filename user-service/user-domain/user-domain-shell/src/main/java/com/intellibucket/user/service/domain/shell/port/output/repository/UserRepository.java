package com.intellibucket.user.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.model.UserEntity;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface UserRepository {
    Optional<UserRoot> findByUserId(UserID userId);

    Optional<UserRoot> update(UserRoot userRoot);

    Optional<UserRoot> delete(UserRoot userRoot);

    Optional<UserRoot> save(UserRoot userRoot);

    Optional<UserRoot> findByEmail(Email email);
}
