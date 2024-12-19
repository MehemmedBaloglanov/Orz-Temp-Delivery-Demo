package com.intellibucket.company.service.domain.shell.dto.rest.command.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CompanyAddressUpdateCommand {
    private String companyId;
}
