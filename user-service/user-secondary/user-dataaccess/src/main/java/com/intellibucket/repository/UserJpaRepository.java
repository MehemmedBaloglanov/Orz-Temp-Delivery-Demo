package com.intellibucket.repository;

import com.intellibucket.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    void updateBy(UserEntity userEntity);

    Optional<UserEntity> findByEmail(String value);
}
