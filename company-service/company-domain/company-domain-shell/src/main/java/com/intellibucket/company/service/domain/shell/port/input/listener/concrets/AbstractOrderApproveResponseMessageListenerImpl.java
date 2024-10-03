package com.intellibucket.company.service.domain.shell.port.input.listener.concrets;

import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponse;
import com.intellibucket.company.service.domain.shell.handler.message.OrderApproveMessageHandler;
import com.intellibucket.company.service.domain.shell.port.input.listener.abstracts.AbstractOrderApproveResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AbstractOrderApproveResponseMessageListenerImpl implements AbstractOrderApproveResponseMessageListener {
    private final OrderApproveMessageHandler orderApproveMessageHandler;
    @Override
    public void approveOrder(ProductApproveResponse response) {
        orderApproveMessageHandler.handle(response);
    }
}
