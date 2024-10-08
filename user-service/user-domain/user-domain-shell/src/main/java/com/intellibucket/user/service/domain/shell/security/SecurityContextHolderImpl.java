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


    @Override
    public UserID currentUserID() {
        return UserID.of(testCurrentCustomerId);

    }}
