package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface OrderRepository {
    OrderRoot save(OrderRoot orderRoot);

    Optional<OrderRoot> findById(OrderID orderId) ;

    Optional<OrderRoot> findByOrderNumber(OrderNumber orderNumber);


    List<OrderRoot> findByUserId(UserID userID);
}