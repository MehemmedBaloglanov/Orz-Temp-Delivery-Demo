package com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderConfirmCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;

public interface OrderCommandServiceAdapter {

    OrderResponse createOrder() throws OrderDomainException;

    void cancelOrder(OrderCancelCommand orderCancelCommand) throws OrderDomainException;

    void confirmOrder(OrderConfirmCommand orderConfirmCommand) throws OrderDomainException;

    void rejectOrder(OrderRejectCommand orderRejectCommand) throws OrderDomainException;
}
