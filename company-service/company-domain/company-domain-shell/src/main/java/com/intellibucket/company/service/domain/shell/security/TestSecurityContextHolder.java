package com.intellibucket.company.service.domain.shell.security;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.courier.CourierID;
import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TestSecurityContextHolder implements AbstractSecurityContextHolder {

    @Value("${test.current.customer.id:#{null}}")
    private String testCurrentCustomerId;


    @Value("${test.current.courier.id:#{null}}")
    private String testCurrentCourierId;

    @Value("${test.current.company.id:#{null}}")
    private String testCurrentCompanyId;


    @Override
    public CustomerID currentCustomerID() {
        return CustomerID.of(testCurrentCustomerId);
    }

    @Override
    public CourierID currentCourierID() {
        return CourierID.of(testCurrentCourierId);
    }

    @Override
    public CompanyID currentCompanyID() {
        return CompanyID.of(testCurrentCompanyId);
    }
}
