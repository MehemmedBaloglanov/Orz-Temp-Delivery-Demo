package com.intellibucket.user.service.domain.shell.dto.request;

import com.intellibucket.user.service.domain.shell.dto.command.abstracts.AbstractUserCreateCommand;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Size
@AllArgsConstructor
@NoArgsConstructor
public class CompanyCreateCommand extends AbstractUserCreateCommand {
    private String companyName;
}