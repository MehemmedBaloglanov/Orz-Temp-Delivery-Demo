package com.intellibucket.company.service.domain.shell.dto.rest.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CompanyDeleteCommand {
    private String companyId;
}
