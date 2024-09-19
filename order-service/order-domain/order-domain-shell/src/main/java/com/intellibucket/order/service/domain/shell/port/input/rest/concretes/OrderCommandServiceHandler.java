package com.intellibucket.order.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.handler.CancelOrderCommandHandler;
import com.intellibucket.order.service.domain.shell.handler.OrderCreateCommandHandler;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command.OrderCommandServiceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
//TODO FIX add orderItemRoot companyId
@RequiredArgsConstructor
public class OrderCommandServiceHandler implements OrderCommandServiceAdapter {
    private final OrderCreateCommandHandler createCommandHandler;
    private final CancelOrderCommandHandler cancelOrderCommandHandler;

    @Override
    public OrderResponse createOrder() throws OrderDomainException {
        return createCommandHandler.handle();
    }

    @Override
    public OrderResponse cancelOrder(OrderCancelCommand orderCancelCommand) {
        return cancelOrderCommandHandler.handle(orderCancelCommand);
    }

    @Override
    public List<OrderResponse> getUnassignedOrders() {
        return List.of();
    }

    @Override
    public void assignOrder(UUID orderId) {

    }
}
