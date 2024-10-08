package com.intellibucket.user.service.primary.rest.command;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.dto.response.EmptyResponse;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command.UserCommandServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
public class UserCommandController {
    private final UserCommandServicePort abstractUserCommandService;

    @PostMapping("/register/company")
    public ResponseEntity<EmptyResponse> registerCompany(@RequestBody CompanyCreateCommand command) throws UserDomainException {
        abstractUserCommandService.companyRegistered(command);
        EmptyResponse response = EmptyResponse.builder().message("User registered successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/customer")
    public ResponseEntity<EmptyResponse> registerCustomer(@RequestBody CustomerCreateCommand command) throws UserDomainException {
        abstractUserCommandService.customerRegistered(command);
        EmptyResponse response = EmptyResponse.builder().message("User registered successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/user")
    public ResponseEntity<EmptyResponse> updateUser(@RequestBody CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
        abstractUserCommandService.userUpdate(command);
        EmptyResponse response = EmptyResponse.builder().message("User updated successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }



    @DeleteMapping("/delete/user")
    public ResponseEntity<EmptyResponse> userDelete(@RequestBody UserDeleteCommand command) throws UserDomainException {
        abstractUserCommandService.userDelete(command);
        return ResponseEntity.ok(EmptyResponse.builder().message("User deleted successfully !").success(true).build());
    }


    @PostMapping("/user/change/password")
    public ResponseEntity<EmptyResponse> userChangePassword(@RequestBody UserChangePasswordCommand command) throws UserDomainException {
        abstractUserCommandService.userChangePassword(command);
        EmptyResponse response = EmptyResponse.builder().message("User changed password successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/user/login")
    public ResponseEntity<EmptyResponse> userLogin(@RequestBody UserLoginCommand command) throws UserDomainException {
        abstractUserCommandService.userLogin(command);
        EmptyResponse response = EmptyResponse.builder().message("User signed in successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }
}