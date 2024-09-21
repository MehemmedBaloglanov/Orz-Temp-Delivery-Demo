package com.intellibucket.user.service.domain.shell.dto.request.component;

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
public class UserRegistrationRequestInfo {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 25)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String firstName;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 25)
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 25)
    @Pattern(regexp = Password.PATTERN)
    private String password;

    @NotNull
    private String birthDate;

    @NotNull
    @Valid
    private String phoneNumber;
    
    @NotNull
    private String gender;

}
