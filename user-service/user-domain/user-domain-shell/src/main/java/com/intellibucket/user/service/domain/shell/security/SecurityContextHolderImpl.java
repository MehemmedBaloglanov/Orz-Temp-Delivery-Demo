package com.intellibucket.user.service.domain.shell.security;

import com.intelliacademy.orizonroute.identity.user.UserID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope
@Component
public class SecurityContextHolderImpl implements AbstractSecurityContextHolder {

    @Value("${env.test.current.customer.id}")
    private String testCurrentCustomerId;

    @Value("${env.test.current.company.id}")
    private String testCurrentCompanyId;


    @Override
    public UserID currentCustomerID() {
        return UserID.of(testCurrentCustomerId);
    }

    @Override
    public UserID currentCompanyID() {
        return UserID.of(testCurrentCompanyId);
    }
}
