package com.intellibucket.user.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intelliacademy.orizonroute.valueobjects.common.PhoneNumber;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRoot extends AggregateRoot<UserID> {
    private final UserID userID;
    private final Address address;
    private RoleAuthorithy roleAuthorithy;
    private Status status;
    private final Password password;
    private final Email email;
    private final PhoneNumber phoneNumber;


    public void initializeUser() {
        setId(UserID.random());
        status = Status.ACTIVE;
    }

    public UserRoot activate() throws UserDomainException {
        if (!status.isCreated()) {
            throw new UserDomainException("Cannot activate user");
        }
        this.status = Status.ACTIVE;
        return this;
    }

    public UserRoot deactivate() throws UserDomainException {
        if (!status.isDeleted()) {
            throw new UserDomainException("Cannot deactivate user");
        }
        this.status = Status.DELETED;
        return this;
    }

    public UserRoot validateUser() throws UserDomainException {
        validatePassword();
        validateEmail();
        return this;
    }

    private void validateAddress() throws UserDomainException {
        if (this.address == null || !address.isAddressValid()) {
            throw new UserDomainException("Address is not valid");
        }
    }

    private void validateEmail() throws UserDomainException {
        if (this.email == null || !email.isValid()) {
            throw new UserDomainException("E-mail is not valid");
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

    public UserRoot changeRole(RoleAuthorithy newRole) throws UserDomainException {
        if (!status.isCreated()) {
            throw new UserDomainException("Cannot change role for inactive user");
        }
        this.roleAuthorithy = newRole;
        return this;
    }
}