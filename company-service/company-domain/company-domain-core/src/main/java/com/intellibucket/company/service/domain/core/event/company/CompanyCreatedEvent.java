package com.intellibucket.company.service.domain.core.event.company;


import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import lombok.Builder;

import java.time.OffsetDateTime;


public class CompanyCreatedEvent extends CompanyEvent {

    public CompanyCreatedEvent(CompanyRoot companyRoot, OffsetDateTime createdTime) {
        super(companyRoot, createdTime);
    }
}
