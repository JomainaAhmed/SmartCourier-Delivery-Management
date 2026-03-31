package com.capg.admin.client;

import com.capg.admin.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthClient {

    @GetMapping("/auth/users")
    List<UserDto> getAllUsers();
}