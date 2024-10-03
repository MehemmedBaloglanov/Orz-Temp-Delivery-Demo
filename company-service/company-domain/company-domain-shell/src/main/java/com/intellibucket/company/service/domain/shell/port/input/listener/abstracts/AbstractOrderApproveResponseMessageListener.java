package com.intellibucket.company.service.domain.shell.port.input.listener.abstracts;

import com.intellibucket.company.service.domain.shell.dto.message.order.approve.ProductApproveResponse;

public interface AbstractOrderApproveResponseMessageListener {
    void approveOrder(ProductApproveResponse response);
}
