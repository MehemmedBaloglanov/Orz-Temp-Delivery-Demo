package com.intellibucket.repository;

import com.intellibucket.model.CustomerRegistrationEntity;
import com.intellibucket.model.UserRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserRegistrationEntity, UUID> {
    Optional<UserRegistrationEntity> findByEmail(String email);
}