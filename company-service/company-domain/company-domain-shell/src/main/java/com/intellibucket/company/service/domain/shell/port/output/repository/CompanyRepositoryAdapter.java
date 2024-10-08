package com.intellibucket.company.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;

import java.util.Optional;

public interface CompanyRepositoryAdapter  {
    CompanyRoot save(CompanyRoot companyRoot);

    Optional<CompanyRoot> findById(CompanyID companyId);

    void delete(CompanyID companyID) throws CompanyDomainException;

}
