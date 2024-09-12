package com.intellibucket.company.service.domain.core.event.company;

import com.intellibucket.company.service.domain.core.root.CompanyRoot;

import java.time.OffsetDateTime;

public class CompanyBannedEvent extends CompanyEvent{

    public CompanyBannedEvent(CompanyRoot companyRoot, OffsetDateTime createdTime) {
        super(companyRoot, createdTime);
    }
}
