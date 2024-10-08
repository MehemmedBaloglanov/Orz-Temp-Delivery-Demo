package com.intellibucket.order.service.domain.shell.port.input.listener.abstracts;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentRefundResponse;

public interface AbstractPaymentRefundMessageListener {
    void completed(PaymentRefundResponse paymentRefundResponse) throws OrderDomainException;
}
