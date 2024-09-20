package com.intellibucket.user.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.model.UserEntity;
import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.util.Optional;

public interface UserRepository {
    Optional<UserRoot> findByUserId(UserID userId);

    UserRoot update(UserRoot userRoot);

    void delete(UserRoot userRoot);

    Optional<UserRoot> save(UserRoot userRoot);
}
