package com.intellibucket.user.service.domain.shell.handler;

import com.intellibucket.user.service.domain.shell.dto.request.CustomerCreateCommand;
import com.intellibucket.user.service.domain.shell.dto.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegisterCommandHandler {
    public CustomerResponse handle(CustomerCreateCommand customerCreateCommand) {
        return null;
    }
}
