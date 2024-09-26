package com.intellibucket.user.service.domain.shell.dto.request;

import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    EmailType emailType;

    private String password;
}