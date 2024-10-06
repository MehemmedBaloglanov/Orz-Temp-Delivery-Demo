package com.intellibucket.order.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderShellHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderSingleFetchQueryHandler {

    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderShellDataMapper orderShellDataMapper;

    public OrderResponse handle(OrderID orderID) throws OrderDomainException {
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        return orderShellDataMapper.orderRootToOrderResponse(orderRoot);
    }
}
