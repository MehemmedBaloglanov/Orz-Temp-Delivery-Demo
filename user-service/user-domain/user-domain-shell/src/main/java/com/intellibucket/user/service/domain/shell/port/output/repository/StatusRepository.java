package com.intellibucket.user.service.domain.shell.port.output.repository;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;

import java.util.List;

public interface StatusRepository {
    List<Status> findByStatusAndRoleAuthority(Status status, RoleAuthorithy roleAuthority);
}