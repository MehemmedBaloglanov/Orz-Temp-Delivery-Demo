package com.intellibucket.company.service.company.repository.adapter;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intellibucket.company.service.company.repository.entity.CompanyJpaEntity;
import com.intellibucket.company.service.company.repository.mapper.CompanyDataAccessMapper;
import com.intellibucket.company.service.company.repository.repository.CompanyJpaRepository;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.shell.port.output.repository.CompanyRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyRepositoryAdapterImpl implements CompanyRepositoryAdapter {

    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyDataAccessMapper companyDataAccessMapper;

    @Override
    public CompanyRoot save(CompanyRoot companyRoot) {
        CompanyJpaEntity companyJpaEntity =  companyDataAccessMapper.mapCompanyRootToCompanyJpaEntity(companyRoot);
        CompanyJpaEntity save = companyJpaRepository.save(companyJpaEntity);
        return companyDataAccessMapper.mapCompanyJpaEntityToCompanyRoot(save);
    }

    @Override
    public Optional<CompanyRoot> findById(CompanyID companyId) {
        Optional<CompanyJpaEntity> company = companyJpaRepository.findById(companyId.value());
        if(company.isEmpty()) {
            return Optional.empty();
        }else {
            CompanyJpaEntity companyJpaEntity = company.get();
            return Optional.of(companyDataAccessMapper.mapCompanyJpaEntityToCompanyRoot(companyJpaEntity));
        }
    }

    @Override
    public void delete(CompanyID companyID) throws CompanyDomainException {
        Optional<CompanyJpaEntity> companyJpaEntity = companyJpaRepository.findById(companyID.value());
        if(companyJpaEntity.isEmpty()) {
            throw new CompanyDomainException("Company does not found with id: " + companyID.value());
        }else {
            CompanyRoot companyRoot = companyDataAccessMapper.mapCompanyJpaEntityToCompanyRoot(companyJpaEntity.get());
            CompanyRoot deletedCompanyEntity = companyRoot.deleted();
            CompanyJpaEntity companyJpaEntityUpdated = companyDataAccessMapper.mapCompanyRootToCompanyJpaEntity(deletedCompanyEntity);
            companyJpaRepository.save(companyJpaEntityUpdated);
        }
    }
}
