package com.intellibucket.company.service.domain.core.service;

import com.intellibucket.company.service.domain.core.event.company.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.root.ProductRoot;

public interface CompanyDomainService {

    CompanyCreatedEvent createCompany(CompanyRoot companyRoot) throws ValidateException;

    CompanyActivatedEvent activateCompany(CompanyRoot companyRoot) throws ValidateException;

    CompanyDeletedEvent deleteCompany(CompanyRoot companyRoot) throws ValidateException;

    CompanySuspendedEvent suspendCompany(CompanyRoot companyRoot) throws ValidateException;

    ProductAddedEvent productAdded(ProductRoot productRoot) throws ValidateException;

    ProductRemovedEvent productRemoved(ProductRoot productRoot) throws ValidateException;



}

