package com.intellibucket.company.service.domain.core.event.company;

import com.intellibucket.company.service.domain.core.root.CompanyRoot;

import java.time.OffsetDateTime;

public class CompanyDeletedEvent extends CompanyEvent{
    public CompanyDeletedEvent(CompanyRoot companyRoot, OffsetDateTime createdTime) {
        super(companyRoot, createdTime);
    }
}
