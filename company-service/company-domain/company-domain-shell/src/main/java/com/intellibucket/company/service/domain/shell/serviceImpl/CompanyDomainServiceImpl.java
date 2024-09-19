package com.intellibucket.company.service.domain.shell.serviceImpl;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.event.company.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;

import java.time.OffsetDateTime;


public class CompanyDomainServiceImpl implements CompanyDomainService {
    @Override
    public CompanyCreatedEvent createCompany(CompanyRoot companyRoot, CompanyID companyID) throws ValidateException {
        companyRoot.initialize();
        return new CompanyCreatedEvent(companyRoot,OffsetDateTime.now());
    }

    @Override
    public CompanyUpdatedEvent updateCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.validateCompany();
        return new CompanyUpdatedEvent(companyRoot,OffsetDateTime.now());
    }

    @Override
    public CompanyActivatedEvent activateCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.activate();
        return new CompanyActivatedEvent(companyRoot,OffsetDateTime.now());
    }

    @Override
    public CompanyDisabledEvent disableCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.suspend();
        return new CompanyDisabledEvent(companyRoot,OffsetDateTime.now());
    }

    @Override
    public CompanyBannedEvent bannedCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.deleted();
        return new CompanyBannedEvent(companyRoot,OffsetDateTime.now());
    }
}
