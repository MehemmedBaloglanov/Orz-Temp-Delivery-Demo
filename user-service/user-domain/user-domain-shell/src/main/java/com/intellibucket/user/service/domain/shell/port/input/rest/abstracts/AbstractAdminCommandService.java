package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;

import java.util.List;

public interface AbstractAdminCommandService {
    public List<RoleAuthorithy> getAllCustomers();

    public List<RoleAuthorithy> getAllCompanies();

    public List<Status> getAllActiveCustomers();

    public List<Status> getAllActiveCompanies();
}