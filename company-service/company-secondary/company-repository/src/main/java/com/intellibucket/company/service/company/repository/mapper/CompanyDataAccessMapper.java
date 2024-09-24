package com.intellibucket.company.service.company.repository.mapper;

import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.company.repository.entity.CompanyJpaEntity;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import org.springframework.stereotype.Component;

@Component
public class CompanyDataAccessMapper {
    public CompanyRoot mapCompanyEntityToCompanyRoot(CompanyJpaEntity companyJpaEntity){
        return CompanyRoot.builder()
                .name(companyJpaEntity.getName())
                .status(companyJpaEntity.getStatus())
                .balance(Money.of(companyJpaEntity.getAmount()))
                .address().build();
    }
}
