package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
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

    public CompanyRoot initializeCompany() {
        super.setId(CompanyID.random());
        status = CompanyStatus.DRAFT;
        validateCompany();
        return this;
    }

    public void validateCompany() {
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

    public CompanyRoot disable() {
        if (!status.isActive() || status.isBanned()) {
            throw new IllegalStateException("Cannot disable Company");
        }
        this.status = CompanyStatus.ACTIVE;
        return this;
    }


    private void validateDescription() {
        if(description == null){
            throw new IllegalArgumentException("Description cannot be null");
        }
    }

    private void validateName() {
        if(name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    private void validateAddress() {
        if(address == null && !address.isValid()){
            throw new IllegalArgumentException("Address cannot be null");
        }
    }


}