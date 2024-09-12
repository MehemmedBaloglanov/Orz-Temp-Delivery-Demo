package com.intellibucket.company.service.domain.shell.serviceImpl;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.company.service.domain.core.event.company.*;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import lombok.Builder;


public class CompanyDomainServiceImpl implements CompanyDomainService {
    @Override
    public CompanyCreatedEvent createCompany(CompanyRoot companyRoot, UserID userID) {
        return null;
    }

    @Override
    public CompanyUpdatedEvent updateCompany(CompanyRoot companyRoot) {
        return null;
    }

    @Override
    public CompanyActivatedEvent activateCompany(CompanyRoot companyRoot) {
        return null;
    }

    @Override
    public CompanyDisabledEvent disableCompany(CompanyRoot companyRoot) {
        return null;
    }

    @Override
    public CompanyBannedEvent bannedCompany(CompanyRoot companyRoot) {
        return null;
    }
}
