package com.intellibucket.user.service.domain.shell.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.user.service.domain.shell.dto.command.abstracts.AbstractUserCreateCommand;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyUpdateCommand {
    @JsonProperty("companyName")
    private String companyName;
}