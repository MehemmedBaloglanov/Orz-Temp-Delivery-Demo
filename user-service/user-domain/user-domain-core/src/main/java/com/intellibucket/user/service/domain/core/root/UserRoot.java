package com.intellibucket.user.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intelliacademy.orizonroute.valueobjects.common.Username;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserRoot extends AggregateRoot<UserID> {
    private final UserID userID;
    private final Address address;
    private RoleAuthorithy roleAuthorithy;
    private Status status;
    private Password password;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final Username username;


    public void initializeUser() {
        setId(UserID.random());
        status = Status.ACTIVE;
    }


    public void delete() {
        this.status = Status.DELETED;
    }

    public void update() {
        this.status = Status.UPDATE;
    }

    public void validateUser() throws UserDomainException {
        validatePassword();
        validateEmail();
    }


    private void validateEmail() throws UserDomainException {
        if (this.email == null || !email.isValid()) {
            throw new UserDomainException("E-mail is not valid");
        }
    }

    private void validateAddress() throws UserDomainException {
        if (this.address == null || !address.isAddressValid()) {
            throw new UserDomainException("Address is not valid");
        }
    }
    private void validatePhoneNumber() throws UserDomainException {
        if (this.phoneNumber == null || !phoneNumber.isValid()) {
            throw new UserDomainException("Phone number is not valid");
        }
    }

    private void validatePassword() throws UserDomainException {
        if (this.password == null || password.getValue().isEmpty()) {
            throw new UserDomainException("Password is not valid");
        }


    }
    //FIXME nezer etmek lazimdir
    public void userChangePassword(Password oldPassword, Password newPassword) throws UserDomainException {
        if (!this.password.isEqual(oldPassword)) {
            throw new UserDomainException("Old password is invalid!");
        }

        if (oldPassword.isEqual(newPassword)) {
            throw new UserDomainException("New password cannot be the same as the old password!");
        }

        this.password = newPassword;
    }

}