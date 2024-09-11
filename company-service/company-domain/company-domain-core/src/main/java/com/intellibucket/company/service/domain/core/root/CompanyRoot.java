package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import com.intellibucket.company.service.domain.core.valueobject.CompanyStatus;
import lombok.Builder;

import java.util.List;

@Builder
public class CompanyRoot extends AggregateRoot<CompanyID> {

    private UserID userID;
    private String name;
    private String description;
    private CompanyAddress address;
    private CompanyStatus status;

    private List<ProductRoot> products;

    public CompanyRoot initializeCompany() throws ValidateException {
        super.setId(CompanyID.random());
        status = CompanyStatus.DRAFT;
        validateCompany();
        return this;
    }

    public void validateCompany() throws ValidateException {
        validateAddress();
        validateName();
        validateDescription();
    }

    public CompanyRoot ban() {
        if (status.isBanned()) {
            throw new IllegalStateException("Company is already banned.");
        }
        this.status = CompanyStatus.BANNED;
        return this;
    }

    public CompanyRoot activate() {
        if (status.isActive()) {
            throw new IllegalStateException("Already activated");
        }
        this.status = CompanyStatus.ACTIVE;
        return this;
    }

    public CompanyRoot disable() throws ValidateException {
        if (!status.isActive() || status.isBanned()) {
            throw new ValidateException("Cannot disable Company");
        }
        this.status = CompanyStatus.ACTIVE;
        return this;
    }


    private void validateDescription() throws ValidateException {
        if(description == null){
            throw new ValidateException("Description cannot be null");
        }
    }

    private void validateName() throws ValidateException {
        if(name == null){
            throw new ValidateException("Name cannot be null");
        }
    }

    private void validateAddress() throws ValidateException {
        if(address == null && !address.isValid()){
            throw new ValidateException("Address cannot be null");
        }
    }


}