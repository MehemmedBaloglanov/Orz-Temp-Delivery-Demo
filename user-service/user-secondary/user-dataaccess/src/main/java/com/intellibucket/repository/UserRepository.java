package com.intellibucket.repository;

import com.intellibucket.model.BaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<BaseUserEntity, UUID> {
    Optional<BaseUserEntity> findByEmail(String email);
}