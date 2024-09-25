package com.intellibucket.repository;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<Status, UUID> {
    List<Status> findByStatusAndRoleAuthority(Status status, RoleAuthorithy roleAuthority);
}