package com.intellibucket.company.service.domain.shell.port.input.rest.concretes;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.service.CompanyDomainService;
import com.intellibucket.company.service.domain.shell.dto.rest.query.CompanyGetByIDQuery;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.CompanyQueryServiceAdapter;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompanyQueryServiceHandler implements CompanyQueryServiceAdapter {
    private final CompanyDomainService companyDomainService;
    private final CompanyRepositoryAdapter companyRepositoryAdapter;


    @Override
    public CompanyResponse getCompanyById(CompanyGetByIDQuery id) throws CompanyDomainException {
        CompanyID companyID = CompanyID.of(id.getCompanyId());
        CompanyRoot companyRoot = companyRepositoryAdapter.findById(companyID)
                .orElseThrow(() -> new CompanyDomainException("Company cannot found with id: " +companyID));
        return null;
    }

    @Override
    public List<CompanyResponse> getAllCompany() throws CompanyDomainException {
        return List.of();
    }
}
