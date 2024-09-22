package com.intellibucket.user.service.domain.shell.dto.request.customer;

import com.intellibucket.user.service.domain.shell.dto.request.component.UserRegistrationRequestInfo;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@AllArgsConstructor
public final class CustomerRegistrationRequestInfo extends UserRegistrationRequestInfo {

}
