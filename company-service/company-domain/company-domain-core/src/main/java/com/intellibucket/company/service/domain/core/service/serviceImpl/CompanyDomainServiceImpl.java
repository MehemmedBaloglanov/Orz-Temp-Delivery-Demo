package com.intellibucket.company.service.domain.core.service.serviceImpl;

import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.company.service.domain.core.event.company.*;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Component
public class CompanyDomainServiceImpl implements CompanyDomainService {

    @Override
    public CompanyCreatedEvent createCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.initialize();
        return new CompanyCreatedEvent(companyRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public CompanyActivatedEvent activateCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.activate();
        return new CompanyActivatedEvent(companyRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public CompanyDeletedEvent deleteCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.deleted();
        return new CompanyDeletedEvent(companyRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public CompanySuspendedEvent suspendCompany(CompanyRoot companyRoot) throws ValidateException {
        companyRoot.suspend();
        return new CompanySuspendedEvent(companyRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public ProductAddedEvent productAdded(CompanyRoot companyRoot,ProductRoot productRoot) throws ValidateException {
        companyRoot.addProduct(productRoot);
        return new ProductAddedEvent(companyRoot, OffsetDateTime.now(ZONE_ID), productRoot);
    }

    @Override
    public ProductRemovedEvent productRemoved(CompanyRoot companyRoot,ProductRoot productRoot) throws ValidateException {
        companyRoot.removeProduct(productRoot);
        return new ProductRemovedEvent(companyRoot, OffsetDateTime.now(ZONE_ID), productRoot);
    }

    @Override
    public CompanyAddressUpdateEvent companyAddressUpdate(CompanyRoot companyRoot, CompanyAddress companyAddress) throws ValidateException {
        companyRoot.changeAddress(companyAddress);
        return new CompanyAddressUpdateEvent(companyRoot, OffsetDateTime.now(ZONE_ID));
    }

    @Override
    public CompanyNameUpdateEvent companyNameUpdate(CompanyRoot companyRoot, Username companyName) throws ValidateException {
        companyRoot.changeName(companyName);
        return new CompanyNameUpdateEvent(companyRoot, OffsetDateTime.now(ZONE_ID));
    }
}
