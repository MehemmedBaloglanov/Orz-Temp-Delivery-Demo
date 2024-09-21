package com.intellibucket.order.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.valueobjects.order.OrderNumber;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.dto.rest.query.OrderTrackingQuery;
import com.intellibucket.order.service.domain.shell.dto.rest.response.TrackOrderResponse;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderTrackQueryHandler {
    private final OrderRepositoryAdapter orderRepository;
    private final OrderShellMapper orderShellMapper;
    public TrackOrderResponse handle(OrderTrackingQuery orderTrackingQuery) throws OrderNotFoundException {
        OrderNumber orderNumber = OrderNumber.of(orderTrackingQuery.getTrackingId());
        Optional<OrderRoot> orderRootOptional = orderRepository.findByOrderNumber(orderNumber);
        if (orderRootOptional.isEmpty()) {
            throw new OrderNotFoundException("Order not found with id " + orderNumber.value());
        }
        OrderRoot orderRoot = orderRootOptional.get();

        return orderShellMapper.orderRootToTrackOrderResponse(orderRoot);
    }
}
