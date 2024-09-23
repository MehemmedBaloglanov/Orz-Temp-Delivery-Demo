package com.intellibucket.company.service.company.repository.adapter;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import org.springframework.stereotype.Component;

@Component
public class CompanyRepositoryAdapterImpl implements CompanyRepositoryAdapter {
    @Override
    public CompanyRoot save(CompanyRoot companyRoot) {
        return null;
    }

    @Override
    public CompanyRoot findById(CompanyID companyId) {
        return null;
    }

    @Override
    public void delete(CompanyRoot companyRoot) {

    }
}
