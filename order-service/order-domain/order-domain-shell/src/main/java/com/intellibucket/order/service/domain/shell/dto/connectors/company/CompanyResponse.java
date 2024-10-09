package com.intellibucket.order.service.domain.shell.dto.connectors.company;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CompanyResponse {
    private CompanyID companyID;
    private CompanyStatus status;
}
