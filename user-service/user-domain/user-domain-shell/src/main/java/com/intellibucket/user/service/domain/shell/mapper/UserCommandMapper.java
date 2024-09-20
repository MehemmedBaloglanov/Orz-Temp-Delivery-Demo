package com.intellibucket.user.service.domain.shell.mapper;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.shell.dto.request.*;

public class UserCommandMapper {

    // CompanyRegisterCommand to UserRoot mapping
    public static UserRoot companyCreateCommandToUserRoot(CompanyCreateCommand command) {
        return UserRoot.builder()
                .email(Email.of(EmailType.NONE,command.getEmail()))
                .password(Password.builder().value(command.getPassword()).build())
                .roleAuthorithy(RoleAuthorithy.COMPANY)
                .username(Username.of(command.getUsername()))
                .build();
    }

    // CustomerRegisterCommand to UserRoot mapping
    public static UserRoot customerCreateCommandToUserRoot(CustomerCreateCommand command) {
        return UserRoot.builder()
                .email(Email.of(EmailType.NONE, command.getEmail()))
                .password(Password.builder().value(command.getPassword()).build())
                .roleAuthorithy(RoleAuthorithy.CUSTOMER)
                .username(Username.of(command.getUsername()))
                .build();
    }
    //UserLoginCommand
    public static UserRoot userLoginCommandToUserRoot(UserLoginCommand command) {
       return UserRoot.builder()
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
    }
    //UserChangePasswordCommand
    public static UserRoot userChangePasswordComandToUserRoot(UserChangePasswordCommand command) {
        return UserRoot.builder()
                .password(Password.builder().value(command.getNewPassword()).build())
                .build();
    }
    //UserDeleteCommand
    public static UserRoot userDeleteCommandToUserRoot(UserDeleteCommand command) {
        return UserRoot.builder()
                .userID(UserID.of(command.getUserid()))
                .build();
    }

}
