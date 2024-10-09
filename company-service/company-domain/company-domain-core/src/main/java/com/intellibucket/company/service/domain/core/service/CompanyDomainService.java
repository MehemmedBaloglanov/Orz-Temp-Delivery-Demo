//package com.intellibucket.company.service.domain.core.service;
//
//import com.intelliacademy.orizonroute.valueobjects.common.Username;
//import com.intellibucket.company.service.domain.core.event.company.*;
//import com.intellibucket.company.service.domain.core.exception.ValidateException;
//import com.intellibucket.company.service.domain.core.root.CompanyRoot;
//import com.intellibucket.company.service.domain.core.root.ProductRoot;
//import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
//
//public interface CompanyDomainService {
//
//    CompanyCreatedEvent createCompany(CompanyRoot companyRoot) throws ValidateException;
//
//    CompanyActivatedEvent activateCompany(CompanyRoot companyRoot) throws ValidateException;
//
//    CompanyDeletedEvent deleteCompany(CompanyRoot companyRoot) throws ValidateException;
//
//    CompanySuspendedEvent suspendCompany(CompanyRoot companyRoot) throws ValidateException;
//
//    ProductAddedEvent productAdded(CompanyRoot companyRoot, ProductRoot productRoot) throws ValidateException;
//
//    ProductRemovedEvent productRemoved(CompanyRoot companyRoot,ProductRoot productRoot) throws ValidateException;
//
//    CompanyAddressUpdateEvent companyAddressUpdate(CompanyRoot companyRoot, CompanyAddress companyAddress) throws ValidateException;
//
//    CompanyNameUpdateEvent companyNameUpdate(CompanyRoot companyRoot, Username companyName) throws ValidateException;
//}
//
