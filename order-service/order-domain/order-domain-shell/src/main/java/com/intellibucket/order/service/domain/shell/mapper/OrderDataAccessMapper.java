package com.intellibucket.order.service.domain.shell.mapper;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCreateCommand;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessMapper {

    // Method to map from domain model (OrderRoot) to entity (OrderEntity)
//    public Company createOrderCommandToCompany(OrderCreateCommand createOrderCommand) {
//        return Company.builder()
//                .restaurantId(new CompanyId(createOrderCommand.getCompanyId()))
//                .products(createOrderCommand.getItems().stream().map(orderItem ->
//                                new Product(new ProductId(orderItem.getProductId())))
//                        .collect(Collectors.toList()))
//                .build();
//    }
//    public OrderItemRoot createOrderCommandToOrder(OrderCreateCommand createOrderCommand) {
//        return OrderItemRoot.builder()
//
//                .build();
//    }


}