package com.intellibucket.order.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.order.service.domain.shell.dto.rest.response.OrderResponse;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCompanyUnassignedQueryHandler {
    private final AbstractSecurityContextHolder securityContextHolder;

    public List<OrderResponse> handle() {
        CompanyID companyID = securityContextHolder.currentCompanyID();

        return null;
    }
}
