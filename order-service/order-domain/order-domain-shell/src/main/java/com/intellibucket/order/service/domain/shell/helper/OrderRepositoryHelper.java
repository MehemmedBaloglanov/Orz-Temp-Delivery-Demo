package com.intellibucket.order.service.domain.shell.helper;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRepositoryHelper {

    private final OrderRepository orderRepository;

    public OrderRoot saveOrder(OrderRoot orderRoot) throws OrderDomainException {
        OrderRoot orderRootResult = orderRepository.save(orderRoot);
        if (orderRootResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderRootResult.getRootID());
        return orderRootResult;
    }
}
