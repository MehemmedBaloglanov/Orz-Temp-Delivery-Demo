package com.intellibucket.user.service.configuration.security;

import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Email userNameValueObject = Email.of(email); // FIX ME - REMOVE EMAIL TYPE
        UserRoot userRoot = userRepository.findByUsername(userNameValueObject)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User
                .withUsername(userRoot.getUsername().value())
                .password(userRoot.getPassword().getValue())
                .authorities(userRoot.getRoleAuthorithy().name())
                .accountLocked(userRoot.getStatus() == Status.DELETED)
                .build();
    }
}