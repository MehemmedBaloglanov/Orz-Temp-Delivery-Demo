package com.intellibucket.user.service.domain.shell.dto.request;

import com.intellibucket.user.service.domain.shell.dto.command.abstracts.AbstractUserCreateCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateCommand extends AbstractUserCreateCommand {
    private String customerId;
    private String firstName;
    private String lastName;
}