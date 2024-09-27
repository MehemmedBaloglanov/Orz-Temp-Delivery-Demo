package com.intellibucket.order.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
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
    private final OrderShellMapper orderShellMapper;
    private final AbstractSecurityContextHolder securityContextHolder;

    public List<OrderResponse> handle() {
        UserID userID = securityContextHolder.currentUserID();
        List<OrderRoot> orderRoots = orderRepository.findByUserId(userID);
        return orderRoots.stream()
                .map(orderShellMapper::orderRootToOrderResponse)
                .collect(Collectors.toList());
    }
}

