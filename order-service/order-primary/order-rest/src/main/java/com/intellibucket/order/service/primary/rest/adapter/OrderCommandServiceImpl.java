package com.intellibucket.order.service.primary.rest.adapter;

import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCreateCommand;
import com.intellibucket.order.service.domain.shell.dto.response.OrderItemResponse;
import com.intellibucket.order.service.domain.shell.dto.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.port.input.rest.command.OrderCommandService;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.repository.mapper.OrderDataAccessMapper;
import com.intellibucket.order.service.repository.model.OrderEntity;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.intellibucket.order.service.domain.core.valueobject.OrderStatus.CREATED;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderCommandServiceImpl(OrderRepository orderRepository, OrderDataAccessMapper orderDataAccessMapper) {
        this.orderRepository = orderRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Transactional
    @Override
    public OrderResponse createOrder(OrderCreateCommand orderCreateCommand) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderCreateCommand.getCartId());

//        orderEntity.setPrice(orderCreateCommand.getPrice());
//        orderEntity.setAddress(orderCreateCommand.getAddress());
        orderEntity.setOrderStatus(CREATED);
     //
        // Save the OrderEntity to the repository (database)
        OrderRoot orderRoot = orderDataAccessMapper.orderEntityToOrderRoot(orderEntity);
        OrderRoot savedOrderRoot = orderRepository.save(orderRoot);



        // Map saved OrderEntity to OrderResponse (a DTO, for example)
        return new OrderResponse(
                savedOrderRoot.getRootID().toString(),                   // id (String formatda)
                orderEntity.getTrackingID(),                      // trackingId
                savedOrderRoot.getStatus(),
                savedOrderRoot.getItems(),
                savedOrderRoot.getAddress(),// status
                savedOrderRoot.getCreateTs()// createdAt (OffsetDateTime)
        );
    }
    @Override
    public OrderResponse cancelOrder(OrderCancelCommand orderCancelCommand) {
        OrderRoot orderRoot = null;
        try {
            orderRoot = orderRepository.findById(orderCancelCommand.getOrderId())
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));



            // Update the order status to "CANCELLED"
            OrderEntity orderEntity = orderDataAccessMapper.orderRootToOrderEntity(orderRoot);
            orderEntity.setOrderStatus(OrderStatus.CANCELLED);

            // Save the updated order back to the repository
            OrderRoot savedOrderRoot =  orderRepository.save(orderRoot);
            return new OrderResponse(
                    savedOrderRoot.getRootID().toString(),                   // id (String formatda)
                    orderEntity.getTrackingID(),
                    savedOrderRoot.getStatus(),// trackingId
                    savedOrderRoot.getItems(),
                    savedOrderRoot.getAddress(),// status
                    savedOrderRoot.getCreateTs()
            );
        } catch (OrderNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderCreateCommand> getUnassignedOrders() {
        return null;
    }

    @Override
    public void assignOrder(UUID orderId) {

    }


}
