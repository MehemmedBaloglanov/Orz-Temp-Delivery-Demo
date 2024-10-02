package com.intellibucket.order.service.repository.adapter;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.repository.mapper.OrderDataAccessMapper;
import com.intellibucket.order.service.repository.model.order.OrderEntity;
import com.intellibucket.order.service.repository.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    @Override
    public OrderRoot save(OrderRoot orderRoot) throws OrderDomainException {
        OrderEntity orderEntity = orderDataAccessMapper.orderRootToOrderEntity(orderRoot);
        OrderEntity saved = orderJpaRepository.save(orderEntity);
        return orderDataAccessMapper.orderEntityToOrderRoot(saved);
    }

    @Override
    public Optional<OrderRoot> findById(OrderID orderId) throws OrderDomainException {
        Optional<OrderEntity> order = orderJpaRepository.findById(orderId.value());
        if (order.isEmpty()) {
            return Optional.empty();
        } else {
            OrderEntity orderEntity = order.get();
            return Optional.of(orderDataAccessMapper.orderEntityToOrderRoot(orderEntity));
        }
    }

    @Override
    public Optional<OrderRoot> findByOrderNumber(OrderNumber orderNumber) throws OrderDomainException {
        Optional<OrderEntity> order = orderJpaRepository.findByOrderNumber(orderNumber.value());
        if (order.isEmpty()) {
            return Optional.empty();
        } else {
            OrderEntity orderEntity = order.get();
            return Optional.of(orderDataAccessMapper.orderEntityToOrderRoot(orderEntity));
        }
    }

    @Override
    public List<OrderRoot> findByUserId(UserID userID) throws OrderDomainException {
        Optional<List<OrderEntity>> orderEntitiesOptional = orderJpaRepository.findByCustomerId(userID.value());
        if (orderEntitiesOptional.isEmpty()) {
            return List.of();
        }
        List<OrderEntity> orderEntities = orderEntitiesOptional.get();
        List<OrderRoot> orderRoots = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orderDataAccessMapper.orderEntityToOrderRoot(orderEntity);
            orderRoots.add(orderDataAccessMapper.orderEntityToOrderRoot(orderEntity));
        }
        return orderRoots;
    }


}
