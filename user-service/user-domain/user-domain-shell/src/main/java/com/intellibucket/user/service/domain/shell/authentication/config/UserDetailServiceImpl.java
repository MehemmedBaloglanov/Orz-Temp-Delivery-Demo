package com.intellibucket.user.service.domain.shell.authentication.config;

import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
        return null;
    }
}

//        Username userRoot = userRepository.findByUsername(Username.of(email))
//                .orElseThrow(() -> new UsernameNotFoundException("User not found for email: " + email));
//
//        return User
//                .withUsername(userRoot.getUsername().value())
//                .password(userRoot.getPassword().getValue())
//                .authorities(userRoot.getRoleAuthorithy().name())
//                .accountLocked(userRoot.getStatus() == Status.DELETED)
//                .build();
//    }