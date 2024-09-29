package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.query;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;

import java.util.List;
import java.util.Optional;

public interface UserQueryServicePort {
    List<RoleAuthorithy> getAllCustomers();

    List<RoleAuthorithy> getAllCompanies();

    List<Status> getUsersByStatusAndByRole(Status status, RoleAuthorithy role);

    Optional<UserRoot> findByUserId(UserID userID);
}