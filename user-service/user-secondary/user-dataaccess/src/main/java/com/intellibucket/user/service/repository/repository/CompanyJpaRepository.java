package com.intellibucket.user.service.repository.repository;

import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyRegistrationEntity, UUID> {
    Optional<CompanyRegistrationEntity> findByEmail(String value);
}