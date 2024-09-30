//package com.intellibucket.repository;
//
//import com.intellibucket.model.BaseUserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository
//public interface UserJpaRepository extends JpaRepository<BaseUserEntity, UUID> {
//    Optional<BaseUserEntity> findByEmail(String value);
//}