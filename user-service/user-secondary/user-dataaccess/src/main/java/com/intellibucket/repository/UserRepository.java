package com.intellibucket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserRegistrationEntity, UUID> {
    Optional<UserRegistrationEntity> findByEmail(String email);
}