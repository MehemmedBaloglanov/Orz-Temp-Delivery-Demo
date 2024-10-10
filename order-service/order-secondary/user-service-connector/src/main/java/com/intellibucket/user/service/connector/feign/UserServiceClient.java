package com.intellibucket.user.service.connector.feign;

import com.intellibucket.user.service.connector.dto.request.UserAddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserServiceClient {
    @GetMapping("/api/1.0/users/{userId}/primary/address")
    UserAddressResponse getUserPrimaryAddress(@PathVariable String userId);
}
