package com.intellibucket.user.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.dto.response.UserAddressResponse;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    UserRoot save(UserRoot userRoot);

    Optional<UserRoot> findByEmail(Email email, UserRoot userRoot);

    List<UserRoot> findByStatusAndRoleAuthority(Status status, RoleAuthorithy role);

    Optional<UserRoot> findByUserId(UserID userId);

    Optional<UserAddressResponse> findAddressByUserId(UserID userId);
}