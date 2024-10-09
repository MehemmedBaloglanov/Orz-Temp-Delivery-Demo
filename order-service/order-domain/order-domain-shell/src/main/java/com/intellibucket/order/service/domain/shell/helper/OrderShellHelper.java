package com.intellibucket.order.service.domain.shell.helper;

import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderShellHelper {
    public OrderItemRoot findOrderItemRootInOrderRoot(OrderRoot orderRoot, OrderItemID orderItemId) throws OrderNotFoundException {
        Optional<OrderItemRoot> orderItemRootOptional = orderRoot.getItems().stream().filter(orderItem -> orderItem.getRootID().equals(orderItemId)).findFirst();

        if (orderItemRootOptional.isEmpty()) {
            log.error("OrderItem with id: {} not found in OrderId: {}", orderItemId, orderRoot.getRootID());
            throw new OrderNotFoundException("OrderItem with id: " + orderItemId + " not found in OrderId: " + orderRoot.getRootID());
        }

        return orderItemRootOptional.get();
    }
}
