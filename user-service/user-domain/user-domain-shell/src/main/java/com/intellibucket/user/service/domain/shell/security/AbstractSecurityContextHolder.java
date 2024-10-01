package com.intellibucket.user.service.domain.shell.security;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.courier.CourierID;
import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface AbstractSecurityContextHolder {
    UserID currentUserID();
    UserID currentNullableUserID();
    CustomerID currentCustomerID();
    CourierID currentCourierID();
    CompanyID currentCompanyID();

}
