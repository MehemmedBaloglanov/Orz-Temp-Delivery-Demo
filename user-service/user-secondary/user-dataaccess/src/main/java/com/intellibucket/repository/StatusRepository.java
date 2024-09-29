//package com.intellibucket.repository;
//
//import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
//import com.intellibucket.user.service.domain.core.valueObject.Status;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//public interface StatusRepository extends JpaRepository<Status, UUID> {
//    List<Status> findByStatusAndRoleAuthority(Status status, RoleAuthorithy roleAuthority);
//}