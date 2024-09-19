package com.intellibucket.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.model.UserEntity;
import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findByUserId(UserID userId);

    void registerAsCompany(UserRoot userRoot);

    void registerAsCustomer(UserRoot userRoot);

    void update(UserRoot userRoot);

    void delete(UserRoot userRoot);

    void changePassword(UserRoot userRoot);
}
