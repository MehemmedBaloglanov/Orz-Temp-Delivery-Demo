package com.intellibucket.user.service.domain.shell.dto.command;

import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserChangePasswordCommand {
    private String oldPassword;
    private Password newPassword;
}
