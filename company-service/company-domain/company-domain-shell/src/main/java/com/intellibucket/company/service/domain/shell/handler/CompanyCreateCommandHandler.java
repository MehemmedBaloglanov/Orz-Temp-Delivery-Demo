package com.intellibucket.company.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;

import java.time.OffsetDateTime;

public class CompanyCreateCommandHandler {
    public CompanyResponse handle() throws CompanyDomainException {
        CompanyID companyID = CompanyID.random();
        Money balance = Money.ZERO;
        //FIXME Builder-i tamamla
        return CompanyResponse.builder()
                .createdAt(OffsetDateTime.now())
                .build();

    }
}
