package com.intellibucket.order.service.domain.shell.security;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.courier.CourierID;
import com.intelliacademy.orizonroute.identity.customer.CustomerID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface AbstractSecurityContextHolder {
    CustomerID currentCustomerID();
    CourierID currentCourierID();
    CompanyID currentCompanyID();


}
