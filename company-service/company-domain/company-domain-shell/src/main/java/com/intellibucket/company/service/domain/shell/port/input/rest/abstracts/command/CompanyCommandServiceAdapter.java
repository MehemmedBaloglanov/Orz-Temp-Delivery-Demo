package com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.*;

public interface CompanyCommandServiceAdapter {

    void changeStatusToSuspend(CompanySuspendedCommand command) throws CompanyDomainException;

    void changeStatusToActive(CompanyActiveCommand command) throws CompanyDomainException;

    void changeStatusToDelete(CompanyDeleteCommand command) throws CompanyDomainException;

    void updateCompanyAddress(CompanyAddressUpdateCommand command) throws CompanyDomainException;

    void updateCompanyName(CompanyNameUpdateCommand command) throws CompanyDomainException;
}
