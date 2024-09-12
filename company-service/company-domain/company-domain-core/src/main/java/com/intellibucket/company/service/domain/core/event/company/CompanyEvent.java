package com.intellibucket.company.service.domain.core.event.company;

import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.event.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Getter
public class CompanyEvent implements DomainEvent<CompanyRoot> {
    private final CompanyRoot companyRoot;
    private final OffsetDateTime createdTime;
}
