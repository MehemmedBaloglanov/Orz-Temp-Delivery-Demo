package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;

import java.util.List;

public interface AbstractAdminCommandService {
    List<RoleAuthorithy> getAllCustomers();

    List<RoleAuthorithy> getAllCompanies();

    List<Status> getUsersByStatusAndByRole(Status status, RoleAuthorithy role);
}