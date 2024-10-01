package com.intellibucket.order.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderConfirmCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.handler.command.OrderCancelCommandHandler;
import com.intellibucket.order.service.domain.shell.handler.command.OrderConfirmCommandHandler;
import com.intellibucket.order.service.domain.shell.handler.command.OrderCreateCommandHandler;
import com.intellibucket.order.service.domain.shell.handler.command.OrderRejectCommandHandler;
import com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command.OrderCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceHandler implements OrderCommandServiceAdapter {
    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderCancelCommandHandler orderCancelCommandHandler;
    private final OrderConfirmCommandHandler orderConfirmCommandHandler;
    private final OrderRejectCommandHandler orderRejectCommandHandler;


    @Override
    public OrderResponse createOrder() throws OrderDomainException {
        return orderCreateCommandHandler.handle();
    }

    @Override
    public void cancelOrder(OrderCancelCommand orderCancelCommand) throws OrderDomainException {
        orderCancelCommandHandler.handle(orderCancelCommand);
    }


    @Override
    public void confirmOrder(OrderConfirmCommand orderAssignCommand) throws OrderDomainException {
        orderConfirmCommandHandler.handle(orderAssignCommand);
    }

    @Override
    public void rejectOrder(OrderRejectCommand orderRejectCommand) throws OrderDomainException {
        orderRejectCommandHandler.handle(orderRejectCommand);
    }


}
