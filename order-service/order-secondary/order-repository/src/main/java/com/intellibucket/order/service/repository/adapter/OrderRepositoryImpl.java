package com.intellibucket.order.service.repository.adapter;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.repository.mapper.OrderDataAccessMapper;
import com.intellibucket.order.service.repository.model.OrderEntity;
import com.intellibucket.order.service.repository.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public OrderRoot save(OrderRoot orderRoot) {
        OrderEntity orderEntity = orderDataAccessMapper.orderRootToOrderEntity(orderRoot);
        OrderEntity saved = orderJpaRepository.save(orderEntity);
        return orderDataAccessMapper.orderEntityToOrderRoot(saved);
    }

    @Override
    public Optional<OrderRoot> findById(OrderID orderId) {
        Optional<OrderEntity> order = orderJpaRepository.findById(orderId.value());
        if (order.isEmpty()) {
            return Optional.empty();
        } else {
            OrderEntity orderEntity = order.get();
            return Optional.of(orderDataAccessMapper.orderEntityToOrderRoot(orderEntity));
        }
    }

    @Override
    public Optional<OrderRoot> findByOrderNumber(OrderNumber orderNumber) {
        Optional<OrderEntity> order = orderJpaRepository.findByOrderNumber(orderNumber.value());
        if (order.isEmpty()) {
            return Optional.empty();
        } else {
            OrderEntity orderEntity = order.get();
            return Optional.of(orderDataAccessMapper.orderEntityToOrderRoot(orderEntity));
        }
    }
}
