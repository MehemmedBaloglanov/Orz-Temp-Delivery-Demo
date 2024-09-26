package com.intellibucket.user.service.domain.shell.mapper;

import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.dto.request.*;

public class UserCommandMapper {

    // CompanyRegisterCommand to UserRoot mapping
    public static UserRoot companyCreateCommandToUserRoot(CompanyCreateCommand command) {
        return UserRoot.builder()
                .email(Email.of(EmailType.NONE,command.getEmail()))
                .password(Password.builder().value(command.getPassword()).build())
                .roleAuthorithy(RoleAuthorithy.COMPANY)
                .username(Username.of(command.getUsername()))
                .status(Status.ACTIVE)
                .build();
    }

    // CustomerRegisterCommand to UserRoot mapping
    public static UserRoot customerCreateCommandToUserRoot(CustomerCreateCommand command) {
        return UserRoot.builder()
                .email(Email.of(EmailType.NONE, command.getEmail()))
                .password(Password.builder().value(command.getPassword()).build())
                .roleAuthorithy(RoleAuthorithy.CUSTOMER)
                .username(Username.of(command.getUsername()))
                .status(Status.ACTIVE)
                .build();
    }
    //UserLoginCommand
    public static UserRoot userLoginCommandToUserRoot(UserLoginCommand command) {
        return UserRoot.builder()
                .email(Email.of(EmailType.NONE, command.getEmail()))
                .password(Password.builder().value(command.getPassword()).build())
                .build();
    }

    public static UserRoot customerUpdateCommandToUserRoot(CustomerUpdateCommand command) {
        return UserRoot.builder()
                .email(Email.of(EmailType.NONE, command.getEmail()))
                .username(Username.generate(command.getFirstName()))
                .username(Username.generate(command.getLastName()))
                .build();

}
    public static UserRoot companyUpdateCommandToUserRoot(CompanyUpdateCommand command) {
        return UserRoot.builder()
                .username(Username.generate(command.getCompanyName()))
                .build();
    }
}
