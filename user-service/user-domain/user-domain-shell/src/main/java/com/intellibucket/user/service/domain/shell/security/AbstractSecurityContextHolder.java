package com.intellibucket.user.service.domain.shell.security;

import com.intelliacademy.orizonroute.identity.user.UserID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface AbstractSecurityContextHolder {

    UserID currentUserID();

}

