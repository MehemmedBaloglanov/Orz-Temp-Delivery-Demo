package com.intellibucket.user.service.domain.shell.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intelliacademy.orizonroute.valueobjects.user.PhoneNumberType;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.shell.dto.command.abstracts.AbstractUserCreateCommand;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerUpdateCommand {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

}