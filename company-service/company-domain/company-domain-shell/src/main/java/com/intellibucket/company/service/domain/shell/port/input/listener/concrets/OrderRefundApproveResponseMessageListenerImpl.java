package com.intellibucket.company.service.domain.shell.port.input.listener.concrets;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.message.order.refund.OrderRefundResponseProduct;
import com.intellibucket.company.service.domain.shell.handler.message.OrderRefundMessageHandler;
import com.intellibucket.company.service.domain.shell.port.input.listener.abstracts.AbstractOrderRefundResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRefundApproveResponseMessageListenerImpl implements AbstractOrderRefundResponseMessageListener {

    private final OrderRefundMessageHandler orderRefundMessageHandler;


    @Override
    public void refundOrder(OrderRefundResponseProduct refundResponseProduct)  {
        orderRefundMessageHandler.handle(refundResponseProduct);
    }
}
