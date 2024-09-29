//package com.intellibucket.repository;
//
//import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//public interface RoleRepository extends JpaRepository<RoleAuthorithy, UUID> {
//    List<RoleAuthorithy> findByAuthority(String authority);
//}