package com.intellibucket.order.service.domain.shell.port.input.listener.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.ApproveResponse;
import com.intellibucket.order.service.domain.shell.handler.message.OrderApproveResponseSagaHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractOrderApproveResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderApproveResponseMessageListener implements AbstractOrderApproveResponseMessageListener {
    private final OrderApproveResponseSagaHandler orderApproveResponseSagaHandler;


    @Override
    public void orderApproved(ApproveResponse approveResponse) throws OrderDomainException {
        orderApproveResponseSagaHandler.process(approveResponse);
    }

    @Override
    public void orderDeclined(ApproveResponse approveResponse) throws OrderDomainException {
        orderApproveResponseSagaHandler.rollback(approveResponse);
    }
}
