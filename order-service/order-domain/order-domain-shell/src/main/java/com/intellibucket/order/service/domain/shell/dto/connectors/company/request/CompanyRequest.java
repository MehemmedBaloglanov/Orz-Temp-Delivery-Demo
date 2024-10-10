package com.intellibucket.order.service.domain.shell.dto.connectors.company.request;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class CompanyRequest {
    private CompanyID companyID;
    private List<CompanyRequestProduct> products;
}
