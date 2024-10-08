package com.intellibucket.company.service.domain.shell.port.input.listener.abstracts;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.message.order.refund.OrderRefundResponseProduct;

public interface AbstractOrderRefundResponseMessageListener {
    void refundOrder(OrderRefundResponseProduct refundResponseProduct);
}
