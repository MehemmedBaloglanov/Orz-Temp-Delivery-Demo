package com.intellibucket.order.service.domain.shell.port.input.listener.abstracts;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;

public interface AbstractPaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse) throws OrderDomainException;

    void paymentCancelled(PaymentResponse paymentResponse) throws OrderDomainException;

}
