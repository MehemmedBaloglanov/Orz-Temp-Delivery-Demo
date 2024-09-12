package com.intellibucket.order.service.primary.rest.command;

import com.intellibucket.order.service.domain.shell.port.input.rest.command.OrderCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/orders")
@RequiredArgsConstructor
public class OrderCommandController {
    private final OrderCommandService orderCommandService;

}
