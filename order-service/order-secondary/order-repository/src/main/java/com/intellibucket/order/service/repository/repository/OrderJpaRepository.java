package com.intellibucket.order.service.repository.repository;

import com.intellibucket.order.service.repository.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    Optional<OrderEntity> findByOrderNumber(String orderNumber);

}
