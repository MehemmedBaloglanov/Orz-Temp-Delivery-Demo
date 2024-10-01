package com.intellibucket.order.service.domain.shell.port.input.listener.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.ApproveResponse;
import com.intellibucket.order.service.domain.shell.handler.message.OrderApproveSagaHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractOrderApproveResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderApproveResponseMessageListener implements AbstractOrderApproveResponseMessageListener {
    private final OrderApproveSagaHandler orderApproveSagaHandler;


    @Override
    public void orderApproved(ApproveResponse approveResponse) throws OrderDomainException {
        orderApproveSagaHandler.process(approveResponse);
    }

    @Override
    public void orderDeclined(ApproveResponse approveResponse) throws OrderDomainException {
        orderApproveSagaHandler.rollback(approveResponse);
    }
}
