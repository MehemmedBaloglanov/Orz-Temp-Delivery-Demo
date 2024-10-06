package com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderPrepareCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderStartDeliveryCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;

public interface OrderCommandServiceAdapter {

    OrderResponse createOrder() throws OrderDomainException;

    void cancelOrder(OrderCancelCommand command) throws OrderDomainException;

    void rejectOrder(OrderRejectCommand command) throws OrderDomainException;
    void startDeliveryOrder(OrderStartDeliveryCommand command) throws OrderDomainException;

    void preparedOrder(OrderPrepareCommand command) throws OrderDomainException;
}
