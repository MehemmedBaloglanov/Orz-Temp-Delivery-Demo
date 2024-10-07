package com.intellibucket.company.service.domain.shell.port.input.listener.abstracts;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.message.order.complete.OrderCompletedResponse;

public interface AbstractOrderCompletedResponseMessageListener {
    void completeOrder(OrderCompletedResponse response) throws CompanyDomainException;
}
