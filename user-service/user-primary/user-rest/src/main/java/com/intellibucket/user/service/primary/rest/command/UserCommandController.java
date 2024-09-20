package com.intellibucket.user.service.primary.rest.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.dto.response.*;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractUserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final AbstractUserCommandService abstractUserCommandService;

    @PostMapping("/register/company")
    public ResponseEntity<CompanyResponse> registerCompany(@RequestBody CompanyCreateCommand command) {
        CompanyResponse companyResponse = abstractUserCommandService.companyRegistered(command);
        return new ResponseEntity<>(companyResponse, HttpStatus.CREATED);
    }

    @PostMapping("/register/customer")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerCreateCommand command) {
        abstractUserCommandService.customerRegistered(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmptyResponse> userDeleted(@PathVariable UserID id) throws UserDomainException {
        abstractUserCommandService.userDeleted(id);
        return ResponseEntity.ok(EmptyResponse.builder().message("User deleted successfully").success(true).build());
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<EmptyResponse> changePassword(@RequestBody UserChangePasswordCommand command) {
//        UserID userID = UserID.of(id); //FIXME abstractSecurity holderdan al.
        abstractUserCommandService.changePassword(command);
        EmptyResponse response = EmptyResponse.builder().message("User deleted successfully").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginCommand command) {
        abstractUserCommandService.userLoggedIn(command);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        return ResponseEntity.ok(userLoginResponse);
    }
    @PostMapping("/update")
    public ResponseEntity<EmptyResponse> userLogin(@RequestBody UserUpdateCommand command) throws UserNotFoundException {
        abstractUserCommandService.updateUser(command);
        EmptyResponse response = EmptyResponse.builder().message("User updated successfully").success(true).build();
    return ResponseEntity.ok(response);
    }

}
