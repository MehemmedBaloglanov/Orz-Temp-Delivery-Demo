package com.intellibucket.user.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<UserRoot> findByUserId(UserID userId);

    UserRoot update(UserRoot userRoot) throws UserNotFoundException;

    void delete(UserRoot userRoot) throws UserNotFoundException;

    UserRoot save(UserRoot userRoot);

    Optional<UserRoot> findByEmail(Email email, UserRoot userRoot) throws UserNotFoundException;

    List<RoleAuthorithy> findByAuthority(String name);

    List<Status> findByStatusAndRoleAuthority(Status status, RoleAuthorithy role);

    Optional<UserRoot> findByUsername(Email email);

    Optional<UserRoot> findByEmailForLogin(Email email);

    void loginUser(UserRoot userRoot);
}