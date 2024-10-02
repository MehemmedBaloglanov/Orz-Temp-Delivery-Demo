package com.intellibucket.user.service.repository.repository;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerRegistrationEntity, UUID> {
    Optional<CustomerRegistrationEntity> findByEmail(String value);
}