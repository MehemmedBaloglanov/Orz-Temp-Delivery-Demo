package com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderAssignCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;

import java.util.List;

public interface OrderCommandServiceAdapter {

    OrderResponse createOrder() throws OrderDomainException;

    void cancelOrder(OrderCancelCommand orderCancelCommand) throws OrderDomainException;

    void assignOrder(OrderAssignCommand orderAssignCommand) throws OrderDomainException;

    void rejectOrder(OrderRejectCommand orderRejectCommand) throws OrderDomainException;
}
