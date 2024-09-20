package com.intellibucket.user.service.domain.shell.dto.request;

import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginCommand {
    private Email email;
    private Password password;
}
