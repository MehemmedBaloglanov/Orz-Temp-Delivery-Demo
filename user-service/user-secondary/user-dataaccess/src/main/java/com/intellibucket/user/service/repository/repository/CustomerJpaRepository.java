package com.intellibucket.user.service.repository.repository;

import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerRegistrationEntity, UUID> {
    Optional<CustomerRegistrationEntity> findByEmail(String value);
}