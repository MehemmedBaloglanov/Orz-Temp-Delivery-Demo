//package com.intellibucket.user.service.domain.shell.security;
//
//import com.intelliacademy.orizonroute.identity.company.CompanyID;
//import com.intelliacademy.orizonroute.identity.courier.CourierID;
//import com.intelliacademy.orizonroute.identity.customer.CustomerID;
//import com.intelliacademy.orizonroute.identity.user.UserID;
//import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@Scope("prototype")
//public class TestSecurityContextHolder implements AbstractSecurityContextHolder {
//
//    @Value("${env.test.current.user.id:#{null}}")
//    private String testCurrentUserId;
//
//    @Value("${env.test.current.customer.id:#{null}}")
//    private String testCurrentCustomerId;
//
//
//    @Value("${env.test.current.courier.id:#{null}}")
//    private String testCurrentCourierId;
//
//    @Value("${env.test.current.company.id:#{null}}")
//    private String testCurrentCompanyId;
//
//    @Override
//    public UserID currentUserID() {
//        return UserID.of(testCurrentUserId);
//    }
//
//    @Override
//    public UserID currentNullableUserID() {
//        return UserID.of(UUID.fromString(testCurrentUserId));
//    }
//    @Override
//    public CustomerID currentCustomerID() {
//        return CustomerID.of(testCurrentCustomerId);
//    }
//
//    @Override
//    public CourierID currentCourierID() {
//        return CourierID.of(testCurrentCourierId);
//    }
//
//    @Override
//    public CompanyID currentCompanyID() {
//        return CompanyID.of(testCurrentCompanyId);
//    }
//}
