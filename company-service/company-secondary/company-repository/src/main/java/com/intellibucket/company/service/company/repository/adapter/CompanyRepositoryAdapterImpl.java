package com.intellibucket.company.service.company.repository.adapter;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.company.repository.entity.CompanyJpaEntity;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyRepositoryAdapterImpl implements CompanyRepositoryAdapter {
    @Override
    public CompanyJpaEntity save(CompanyRoot companyRoot) {
        return null;
    }

    @Override
    public Optional<CompanyJpaEntity> findById(CompanyID companyId) {
        return null;
    }

    @Override
    public void delete(CompanyRoot companyRoot) {

    }
}
