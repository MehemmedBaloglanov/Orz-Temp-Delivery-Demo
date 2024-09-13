package com.intellibucket.company.service.domain.core.service;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.company.service.domain.core.event.company.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;

public interface CompanyDomainService {

    CompanyCreatedEvent createCompany(CompanyRoot companyRoot, CompanyID companyID) throws ValidateException;

    CompanyUpdatedEvent updateCompany(CompanyRoot companyRoot) throws ValidateException;

    CompanyActivatedEvent activateCompany(CompanyRoot companyRoot) throws ValidateException;

    CompanyDisabledEvent disableCompany(CompanyRoot companyRoot) throws ValidateException;

    CompanyBannedEvent bannedCompany(CompanyRoot companyRoot) throws ValidateException;


}

