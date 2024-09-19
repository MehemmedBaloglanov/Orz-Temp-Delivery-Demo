package com.intellibucket.order.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderCommandServiceAdapter {

    OrderResponse createOrder();

    OrderResponse cancelOrder(OrderCancelCommand orderCancelCommand);

    List<OrderResponse> getUnassignedOrders();

    void assignOrder(UUID orderId);
}
