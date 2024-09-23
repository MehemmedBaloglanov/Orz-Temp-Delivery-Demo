package com.intellibucket.company.service.domain.shell.port.output.repository;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;

public interface CompanyRepositoryAdapter {
    CompanyRoot save(CompanyRoot companyRoot);

    CompanyRoot findById(CompanyID companyId);

    void delete(CompanyRoot companyRoot);

}
