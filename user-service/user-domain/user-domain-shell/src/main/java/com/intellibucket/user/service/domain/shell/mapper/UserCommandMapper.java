package com.intellibucket.user.service.domain.shell.mapper;

import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.shell.dto.command.CompanyRegisterCommand;
import com.intellibucket.user.service.domain.shell.dto.command.CustomerRegisterCommand;
import com.intellibucket.user.service.domain.shell.dto.command.UserChangePasswordCommand;
import com.intellibucket.user.service.domain.shell.dto.command.UserLoginCommand;

public class UserCommandMapper {

    // CompanyRegisterCommand to UserRoot mapping
    public static UserRoot toUserRoot(CompanyRegisterCommand command) {
        return UserRoot.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .roleAuthorithy(RoleAuthorithy.COMPANY)
                .username(command.getCompanyName())
                .build();
    }

    // CustomerRegisterCommand to UserRoot mapping
    public static UserRoot toUserRoot(CustomerRegisterCommand command) {
        return UserRoot.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .roleAuthorithy(RoleAuthorithy.CUSTOMER)
                .username(command.getCustomerName())
                .build();
    }
    //UserLoginCommand
    public static UserRoot toUserRoot(UserLoginCommand command) {
       return UserRoot.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
    }
    //UserchangePasswordCommand
    public static UserRoot toUserRoot(UserChangePasswordCommand command) {
        return UserRoot.builder()
                .password(command.getNewPassword())
                .build();
    }
}
