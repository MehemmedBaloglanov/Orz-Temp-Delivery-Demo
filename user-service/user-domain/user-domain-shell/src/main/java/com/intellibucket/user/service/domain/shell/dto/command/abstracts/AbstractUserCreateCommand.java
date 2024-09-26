package com.intellibucket.user.service.domain.shell.dto.command.abstracts;

import com.intellibucket.user.service.domain.core.valueObject.Password;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractUserCreateCommand {


    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 25)
    @Pattern(regexp = Password.PATTERN)
    private String password;


    @NotNull
    @Valid
    private String phoneNumber;

    @NotNull
    private String address;

    @NotNull
    private String username;


}
