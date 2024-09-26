package com.intellibucket.user.service.domain.shell.dto.request;

import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginCommand {
    private String email;
    private String password;
}
