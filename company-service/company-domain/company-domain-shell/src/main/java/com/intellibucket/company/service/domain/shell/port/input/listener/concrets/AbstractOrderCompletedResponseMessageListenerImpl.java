package com.intellibucket.company.service.domain.shell.port.input.listener.concrets;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.message.order.complete.OrderCompletedResponse;
import com.intellibucket.company.service.domain.shell.handler.message.OrderCompletedMessageHandler;
import com.intellibucket.company.service.domain.shell.port.input.listener.abstracts.AbstractOrderCompletedResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbstractOrderCompletedResponseMessageListenerImpl implements AbstractOrderCompletedResponseMessageListener {
    private final OrderCompletedMessageHandler orderCompletedMessageHandler;
    @Override
    public void completeOrder(OrderCompletedResponse response) throws CompanyDomainException {
        orderCompletedMessageHandler.handle(response);
    }
}
