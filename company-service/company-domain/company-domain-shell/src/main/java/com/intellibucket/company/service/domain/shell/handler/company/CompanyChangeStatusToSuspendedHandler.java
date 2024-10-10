//package com.intellibucket.company.service.domain.shell.handler;
//
//import com.intelliacademy.orizonroute.identity.company.CompanyID;
//import com.intellibucket.company.service.domain.core.event.company.CompanySuspendedEvent;
//import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
//import com.intellibucket.company.service.domain.core.root.CompanyRoot;
//import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
//import com.intellibucket.company.service.domain.shell.dto.rest.command.company.CompanySuspendedCommand;
//import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
//import lombok.RequiredArgsConstructor;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class CompanyChangeStatusToSuspendedHandler {
//    private final CompanyRepositoryAdapter companyRepositoryAdapter;
//    private final CompanyDomainService companyDomainService;
//
//    public void handle(CompanySuspendedCommand command) throws CompanyDomainException {
//        CompanyID companyID=CompanyID.of(command.getCompanyId());
//
//        Optional<CompanyRoot> companyRoot=companyRepositoryAdapter.findById(companyID);
//
//        if (companyRoot.isEmpty()) {
//            throw new CompanyDomainException("Company does not found with ID: " + companyID);
//        }
//
//        CompanySuspendedEvent companySuspendedEvent = companyDomainService.suspendCompany(companyRoot.get());
//        companyRepositoryAdapter.save(companyRoot.get());
//    }
//}
