package com.intellibucket.user.service.domain.shell.dto.request;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserChangePasswordCommand {
    private String oldPassword;
    private String newPassword;
}