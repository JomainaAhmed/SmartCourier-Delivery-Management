package com.capg.auth.mapper;

import com.capg.auth.dto.SignupRequest;
import com.capg.auth.dto.Response;
import com.capg.auth.entity.User;

public class UserMapper {

    public static User toEntity(SignupRequest dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole("USER");
        return user;
    }
    
    
    public static Response toResponse(String token) {
        return new Response(token);
    }
}