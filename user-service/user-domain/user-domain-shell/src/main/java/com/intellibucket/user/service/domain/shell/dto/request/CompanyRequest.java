package com.intellibucket.user.service.domain.shell.dto.request;

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
public class CompanyRequest extends UserRequest {
    @NotEmpty
    @NotNull
    @Size(min = 4,max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    private String companyName;
    private String licenceNumber;
}
