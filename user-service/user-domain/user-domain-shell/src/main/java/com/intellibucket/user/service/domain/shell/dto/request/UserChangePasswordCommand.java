package com.intellibucket.user.service.domain.shell.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChangePasswordCommand {
    String oldPassword;
    String newPassword;
}