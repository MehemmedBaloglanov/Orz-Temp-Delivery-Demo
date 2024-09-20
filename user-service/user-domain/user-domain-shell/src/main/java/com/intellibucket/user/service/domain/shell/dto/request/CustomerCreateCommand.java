package com.intellibucket.user.service.domain.shell.dto.request;

import com.intellibucket.user.service.domain.shell.dto.command.abstracts.AbstractUserCreateCommand;
import com.intellibucket.user.service.domain.shell.dto.response.CustomerResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public  class CustomerCreateCommand extends AbstractUserCreateCommand {
    @NotEmpty
    @NotNull
    @Size(min = 4,max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private  String firstName;
    private  String lastName;
    private String gender;
    private String birthDate;

}
