package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import com.intellibucket.company.service.domain.core.valueobject.CompanyStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public class CompanyRoot extends AggregateRoot<CompanyID> {

    private UserID userID;
    private String name;
    private String description;
    private CompanyAddress address;
    private CompanyStatus companyStatus;
    private List<ProductRoot> productRoots;
}
