package com.intellibucket.order.service.domain.shell.port.input.listener.abstracts;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.ApproveResponse;

public interface AbstractOrderApproveResponseMessageListener {

    /**
     * Handles the approval of an order.
     *
     * @param approveResponse the response containing approval details.
     * @throws OrderDomainException if an error occurs during the processing of the approval.
     */
    void orderApproved(ApproveResponse approveResponse) throws OrderDomainException;


    /**
     * Handles the decline of an order.
     *
     * @param approveResponse the response containing decline details.
     * @throws OrderDomainException if an error occurs during the processing of the decline.
     */
    void orderDeclined(ApproveResponse approveResponse) throws OrderDomainException;
}
