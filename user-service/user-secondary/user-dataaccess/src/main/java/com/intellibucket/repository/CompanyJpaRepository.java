package com.intellibucket.repository;

import com.intellibucket.model.CompanyRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyJpaRepository extends JpaRepository<CompanyRegistrationEntity, UUID> {
    Optional<CompanyRegistrationEntity> findByEmail(String value);
}