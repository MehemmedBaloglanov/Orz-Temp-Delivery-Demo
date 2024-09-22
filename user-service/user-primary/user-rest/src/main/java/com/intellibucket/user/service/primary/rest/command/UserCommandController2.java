package com.intellibucket.user.service.primary.rest.command;

import com.intellibucket.mapper.DataAccessMapper;
import com.intellibucket.repository.UserJpaRepository;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.service.UserDomainService2;
import com.intellibucket.user.service.domain.shell.dto.request.CompanyCreateCommand;
import com.intellibucket.user.service.domain.shell.dto.response.CompanyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserCommandController2 {
    private final UserDomainService2 userDomainService2;
//    private final DataAccessMapper dataAccessMapper;
    private final UserJpaRepository userJpaRepository;

    @PostMapping("/registerAsCompany")
    public ResponseEntity<CompanyResponse> registerAsCompany(@RequestBody CompanyCreateCommand userCreateCommand) {
        UserRoot user = DataAccessMapper.customerRegistrationEntitytoUserRoot(customerRegistrationEntity);
        userDomainService2.registerAsCompany(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//
//    @PostMapping("/registerAsCustomer")
//    public ResponseEntity<Void> registerAsCustomer(@RequestBody UserCreateCommand userCreateCommand) {
//        UserRoot user = userCreateCommand.toUserRoot();
//        userDomainService2.registerAsCustomer(user);
//        log.info("Customer with id {{}} is registered!", userCreateCommand.getId());
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

//    @PostMapping("/login")
//    public ResponseEntity<UserLoginResponse> userLoggedIn(@RequestBody UserLoginCommand userLoginCommand) {
//        boolean isAuthenticated = userDomainService2.userLoggedIn(userLoginCommand.getEmail(), userLoginCommand.getPassword());
//        if (isAuthenticated) {
//            CustomerRegistrationEntity customerRegistrationEntity = new CustomerRegistrationEntity();
//            // Create a response object with the necessary details
//            CustomerResponse customerResponse = new CustomerResponse(
//                    customerRegistrationEntity.getUsername()
//            );
//            log.info("A user with ID {{}} is logged in!", customerResponse.getUsername());
//            return null;
////            return ResponseEntity.ok("Login successful! You are logged in!");
//
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body((new UserLoginResponse("Invalid credentials! Try again!", "", "")));
//        }
//    }

    @PostMapping("/{userId}/change-password")
    public void changePassword(@PathVariable UUID userId, String oldPassword, String newPassword) {
        userDomainService2.changePassword(userId, oldPassword, newPassword);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        userDomainService2.markUserAsInactive(userId);
        return ResponseEntity.noContent().build();
    }
}