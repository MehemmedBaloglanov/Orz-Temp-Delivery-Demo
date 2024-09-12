package com.intellibucket.user.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.valueObject.*;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRoot extends AggregateRoot<UserID> {
    private final UserID userID;
    private final Address address;
    private ContactInfo contactInfo;
    private final DateOfBirth dateOfBirth;
    private RoleAuthorithy roleAuthorithy;
    private Status status;
    private final Password password;
    private final Name name;
    private final Email email;

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
        validateAddress();
        validatePassword();
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

    public UserRoot updateContactInfo(ContactInfo newContactInfo) throws UserDomainException {
        if (!status.isUpdate()) {
            throw new UserDomainException("Cannot update contact info for inactive user");
        }
        this.contactInfo = newContactInfo;
        return this;
    }
}



