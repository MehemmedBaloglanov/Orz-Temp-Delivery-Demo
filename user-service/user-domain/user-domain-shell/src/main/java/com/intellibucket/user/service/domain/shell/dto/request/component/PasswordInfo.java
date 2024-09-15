package com.intellibucket.user.service.domain.shell.dto.request.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PasswordInfo {

    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;
}
