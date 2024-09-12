package com.intellibucket.order.service.domain.shell.port.input.rest.command;

import com.intellibucket.order.service.domain.shell.dto.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCreateCommand;
import com.intellibucket.order.service.domain.shell.dto.response.OrderResponse;

public interface OrderCommandService {

    OrderResponse createOrder(OrderCreateCommand orderCreateCommand);

    OrderResponse cancelOrder(OrderCancelCommand orderCancelCommand);

}
