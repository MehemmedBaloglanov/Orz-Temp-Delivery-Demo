package com.intellibucket.order.service.domain.shell.port.input.listener.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentRefundResponse;
import com.intellibucket.order.service.domain.shell.handler.message.PaymentRefundResponseHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractPaymentRefundMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRefundMessageListener implements AbstractPaymentRefundMessageListener {
    private final PaymentRefundResponseHandler paymentRefundResponseHandler;
    @Override
    public void completed(PaymentRefundResponse paymentRefundResponse) throws OrderDomainException {
        paymentRefundResponseHandler.handle(paymentRefundResponse);
    }
}
