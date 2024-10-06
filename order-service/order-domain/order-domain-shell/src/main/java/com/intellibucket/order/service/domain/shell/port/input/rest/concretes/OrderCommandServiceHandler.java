package com.intellibucket.order.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderPrepareCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderStartDeliveryCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.handler.command.*;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command.OrderCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceHandler implements OrderCommandServiceAdapter {
    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderCancelCommandHandler orderCancelCommandHandler;
    private final OrderRejectCommandHandler orderRejectCommandHandler;
    private final OrderPrepareCommandHandler orderPrepareCommandHandler;
    private final OrderStartDeliveryCommandHandler orderStartDeliveryCommandHandler;


    @Override
    public OrderResponse createOrder() throws OrderDomainException {
        return orderCreateCommandHandler.handle();
    }

    @Override
    public void cancelOrder(OrderCancelCommand command) throws OrderDomainException {
        orderCancelCommandHandler.handle(command);
    }

    @Override
    public void rejectOrder(OrderRejectCommand command) throws OrderDomainException {
        orderRejectCommandHandler.handle(command);
    }

    @Override
    public void startDeliveryOrder(OrderStartDeliveryCommand command) throws OrderDomainException {
        orderStartDeliveryCommandHandler.handle(command);
    }

    @Override
    public void preparedOrder(OrderPrepareCommand command) throws OrderDomainException {
        orderPrepareCommandHandler.handle(command);
    }


}
