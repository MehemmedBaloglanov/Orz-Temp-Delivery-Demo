package com.intellibucket.company.service.company.repository.repository;

import com.intellibucket.company.service.company.repository.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, UUID> {
}
