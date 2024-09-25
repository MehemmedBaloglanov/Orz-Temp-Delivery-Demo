package com.intellibucket.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyRegistrationEntity extends BaseUserEntity {
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    String companyName;
}