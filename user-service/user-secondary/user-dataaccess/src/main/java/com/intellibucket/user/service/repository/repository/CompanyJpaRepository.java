package com.intellibucket.user.service.repository.repository;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CompanyJpaRepository extends JpaRepository<CompanyRegistrationEntity, UUID> {
    Optional<CompanyRegistrationEntity> findByEmail(String value);

    List<CompanyRegistrationEntity> findByStatusAndRoleAuthority(Status status, RoleAuthorithy roleAuthority);
}