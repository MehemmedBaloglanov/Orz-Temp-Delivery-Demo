package com.intellibucket.order.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderSingleFetchQueryHandler {
    public OrderResponse handle(OrderID orderID) {
        return null;
    }
}
