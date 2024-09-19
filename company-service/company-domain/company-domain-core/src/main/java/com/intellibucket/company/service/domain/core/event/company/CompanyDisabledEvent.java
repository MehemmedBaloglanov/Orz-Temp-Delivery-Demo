package com.intellibucket.company.service.domain.core.event.company;

import com.intellibucket.company.service.domain.core.root.CompanyRoot;

import java.time.OffsetDateTime;

public class CompanyDisabledEvent extends CompanyEvent{
    public CompanyDisabledEvent(CompanyRoot companyRoot, OffsetDateTime createdTime) {
        super(companyRoot, createdTime);
    }
}
