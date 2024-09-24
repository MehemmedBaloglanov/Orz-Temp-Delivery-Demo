package com.intellibucket.company.service.domain.shell.dto.rest.response;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import com.intellibucket.company.service.domain.core.valueobject.CompanyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class CompanyResponse {
//        private final CompanyID id;

        private final CompanyAddress address;

//        private final CompanyStatus status;

        private final List<ProductRoot> products;

//        private final Money balance;

        private final OffsetDateTime createdAt;
}
