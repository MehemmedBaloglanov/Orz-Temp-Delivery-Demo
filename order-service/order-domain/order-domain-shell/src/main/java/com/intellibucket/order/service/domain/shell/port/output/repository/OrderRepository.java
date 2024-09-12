package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.root.OrderRoot;

import java.util.Optional;

public interface OrderRepository {
    OrderRoot save(OrderRoot orderRoot);

    Optional<OrderRoot> findById(OrderID orderId);

    Optional<OrderRoot> findByOrderNumber(OrderNumber orderNumber);

}