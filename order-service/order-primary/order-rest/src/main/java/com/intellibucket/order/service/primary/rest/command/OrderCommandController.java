package com.intellibucket.order.service.primary.rest.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.dto.command.OrderCreateCommand;
import com.intellibucket.order.service.domain.shell.port.input.rest.command.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/orders")
@RequiredArgsConstructor
public class OrderCommandController {
    private final OrderCommandService orderCommandService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderCreateCommand createOrderCommand) {
        orderCommandService.createOrder(createOrderCommand);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }


    @PutMapping("/cancel")
    public ResponseEntity<String> cancelOrder(OrderCancelCommand orderCancelCommand) {
        orderCommandService.cancelOrder(orderCancelCommand);
        return new ResponseEntity<>("Order canceled successfully", HttpStatus.OK);
    }

}
