package com.intellibucket.user.service.domain.shell.dto.request;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserChangePasswordCommand {
    private String oldPassword;
    private String newPassword;
}