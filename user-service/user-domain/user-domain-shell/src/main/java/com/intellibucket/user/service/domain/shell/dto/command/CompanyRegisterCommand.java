package com.intellibucket.user.service.domain.shell.dto.command;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import lombok.Builder;
import lombok.Getter;
    @Getter
    @Builder
    public class CompanyRegisterCommand {
        private Email email;
        private Password password;
        private Username companyName;
        private Address companyAddress;
    }


