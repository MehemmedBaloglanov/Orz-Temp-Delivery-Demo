package com.intellibucket.company.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.company.service.domain.core.event.company.*;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;

public interface CompanyDomainService {

    CompanyCreatedEvent createCompany(CompanyRoot companyRoot, UserID userID);

    CompanyUpdatedEvent updateCompany(CompanyRoot companyRoot);

    CompanyActivatedEvent activate(CompanyRoot companyRoot);

    CompanyDisabledEvent disable(CompanyRoot companyRoot);

    CompanyBannedEvent banned(CompanyRoot companyRoot);


}

