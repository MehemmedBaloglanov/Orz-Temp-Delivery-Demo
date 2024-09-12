package com.intellibucket.order.service.repository.adapter;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;

import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public OrderRoot save(OrderRoot orderRoot) {
        return null;
    }

    @Override
    public Optional<OrderRoot> findById(OrderID orderId) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderRoot> findByOrderNumber(OrderNumber orderNumber) {
        return Optional.empty();
    }
}
