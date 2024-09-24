package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyDeleteCommand;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.CompanyCommandServiceAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompanyCommandServiceHandler implements CompanyCommandServiceAdapter {


    @Override
    public void changeStatusToSuspend(CompanyDeleteCommand command) throws CompanyDomainException {
        return ;
    }
}
