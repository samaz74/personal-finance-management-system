package com.accounting.app.controller;

import com.accounting.app.dto.LoginRequest;
import com.accounting.app.dto.UserRequest;
import com.accounting.app.dto.mapper.AuthResponse;
import com.accounting.app.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse registerAccount(@RequestBody UserRequest userRequest){
        return authService.registerUser(userRequest);
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authHeder){
        authService.logout(authHeder.substring(7));
    }
}
