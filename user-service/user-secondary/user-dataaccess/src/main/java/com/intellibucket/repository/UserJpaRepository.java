package com.intellibucket.repository;

import com.intellibucket.model.BaseUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<BaseUserEntity, UUID> {

    void updateBy(BaseUserEntity userEntity);

    Optional<BaseUserEntity> findByEmail(String value);
}
