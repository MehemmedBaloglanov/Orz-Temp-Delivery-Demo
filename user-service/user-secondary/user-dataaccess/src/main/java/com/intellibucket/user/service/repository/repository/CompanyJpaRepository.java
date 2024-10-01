package com.intellibucket.user.service.repository.repository;

import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyJpaRepository extends JpaRepository<CompanyRegistrationEntity, UUID> {
    void updateBy(CompanyRegistrationEntity userEntity);

    Optional<CompanyRegistrationEntity> findByEmail(String value);
}