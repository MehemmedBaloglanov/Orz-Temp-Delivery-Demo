package com.intellibucket.company.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import com.intellibucket.company.service.domain.core.valueobject.CompanyStatus;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class CompanyRoot extends AggregateRoot<CompanyID> {

    private String name;
    private String description;
    private CompanyAddress address;
    private CompanyStatus status;
    private List<ProductRoot> products;
    private Money balance;



    public CompanyRoot initialize() throws ValidateException {
        super.setId(CompanyID.random());

        if (status == null) {
            status = CompanyStatus.DRAFT;
        }
        if (balance == null) {
            balance = Money.of(0);
        }
        validateCompany();
        return this;
    }


    //-------------------------------------->VALIDATE FIELDS

    public void validateCompany() throws ValidateException {
        validateAddress();
        validateName();
        validateDescription();
        validateProducts();
        validateBalance();
    }

    private void validateDescription() throws ValidateException {
        if (description == null || description.trim().isEmpty()) {
            throw new ValidateException("Description cannot be null or empty");
        }
    }

    private void validateName() throws ValidateException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidateException("Name cannot be null or empty");
        }
    }

    private void validateAddress() throws ValidateException {
        if (address == null || !address.isValid()) {
            throw new ValidateException("Address is not valid");
        }
    }

    //todo: isGreaterThanZero() validate i duzgundurmu?
    private void validateBalance() throws ValidateException {
        if (balance == null || !balance.isGreaterThanZero()) {
            throw new ValidateException("Balance cannot be null or negative");
        }
    }


    private void validateProducts() throws ValidateException {
        if (products == null || products.isEmpty()) {
            throw new ValidateException("Products list cannot be empty");
        }
    }



    //-------------------------------------->UPDATE COMPANY STATUS

    public CompanyRoot deleted() throws ValidateException {
        if (status.isDeleted()) {
            throw new ValidateException("Company is already deleted.");
        }
        this.status = CompanyStatus.DELETED;
        return this;
    }

    public CompanyRoot activate() throws ValidateException {
        if (status.isActive()) {
            throw new ValidateException("Already activated");
        }
        this.status = CompanyStatus.ACTIVE;
        return this;
    }

    //todo: burda diable methodunu suspended olaraq deyisdirdim duzgundurmu?
    public CompanyRoot suspend() throws ValidateException {
        if (!status.isActive() || status.isDeleted()) {
            throw new ValidateException("Only active companies can be suspended, and deleted companies cannot be suspended.");
        }
        this.status = CompanyStatus.SUSPENDED;
        return this;
    }



    //------------------------------------->UPDATE OTHER FIELDS

    public CompanyRoot changeDescription(String newDescription) throws ValidateException {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new ValidateException("New description cannot be null or empty");
        }
        this.description = newDescription;
        return this;
    }

    public CompanyRoot changeAddress(CompanyAddress newAddress) throws ValidateException {
        if (newAddress == null || !newAddress.isValid()) {
            throw new ValidateException("New address is not valid");
        }
        this.address = newAddress;
        return this;
    }

    //todo: isGreaterThanZero() validate i duzgundurmu?
    public CompanyRoot updateBalance(Money newBalance) throws ValidateException {
        if (newBalance == null || !newBalance.isGreaterThanZero()) {
            throw new ValidateException("Balance cannot be null or negative");
        }
        this.balance = newBalance;
        return this;
    }


    //------------------------------------->PRODUCT ADD AND REMOVE
    //todo: Burda biz ProductRoot u elave elemeliyik yoxsa ProductRoot -u onu tam emin deyilem
    public CompanyRoot addProduct(ProductRoot product) throws ValidateException {
        if (product == null) {
            throw new ValidateException("Product cannot be null.");
        }
        if (products.contains(product)) {
            throw new ValidateException("Product is already added to the company.");
        }
        products.add(product);
        return this;
    }

    public CompanyRoot removeProduct(ProductRoot product) throws ValidateException {
        if (product == null) {
            throw new ValidateException("Product cannot be null.");
        }
        if (!products.contains(product)) {
            throw new ValidateException("Product does not exist in the company.");
        }
        products.remove(product);
        return this;
    }


}