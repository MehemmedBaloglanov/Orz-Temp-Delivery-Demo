package com.intellibucket.user.service.domain.shell.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse{
    private String companyName;
    private String licenceNumber;
}
