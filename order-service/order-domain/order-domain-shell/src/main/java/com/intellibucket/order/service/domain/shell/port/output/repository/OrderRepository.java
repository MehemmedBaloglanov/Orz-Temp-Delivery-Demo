package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface OrderRepository {
    OrderRoot save(OrderRoot orderRoot) throws OrderDomainException;

    Optional<OrderRoot> findById(OrderID orderId) throws OrderDomainException;

    Optional<OrderRoot> findByOrderNumber(OrderNumber orderNumber) throws OrderDomainException;


    List<OrderRoot> findByCustomerId(CustomerID customerID) throws OrderDomainException;
}