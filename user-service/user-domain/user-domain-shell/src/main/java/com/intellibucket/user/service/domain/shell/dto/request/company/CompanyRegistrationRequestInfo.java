package com.intellibucket.user.service.domain.shell.dto.request.company;

import com.intellibucket.user.service.domain.shell.dto.request.component.UserRegistrationRequestInfo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@Size
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegistrationRequestInfo extends UserRegistrationRequestInfo {
    @NotEmpty
    @NotNull
    @Size(min = 4,max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private String name;
    private String license;
}
