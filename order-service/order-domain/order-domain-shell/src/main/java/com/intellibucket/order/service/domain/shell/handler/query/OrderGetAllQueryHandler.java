package com.intellibucket.order.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellDataMapper;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderGetAllQueryHandler {
    private final OrderRepository orderRepository;
    private final OrderShellDataMapper orderShellDataMapper;
    private final AbstractSecurityContextHolder securityContextHolder;

    public List<OrderResponse> handle() throws OrderDomainException {
        CustomerID customerID = securityContextHolder.currentCustomerID();
        List<OrderRoot> orderRoots = orderRepository.findByCustomerId(customerID);
        return orderRoots.stream()
                .map(orderShellDataMapper::orderRootToOrderResponse)
                .collect(Collectors.toList());
    }
}

