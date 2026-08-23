package com.addressbook.user.controller;

import com.addressbook.user.dto.AuthResponse;
import com.addressbook.user.dto.LoginRequest;
import com.addressbook.user.dto.RegisterRequest;
import com.addressbook.user.dto.TokenValidationResponse;
import com.addressbook.user.security.JwtService;
import com.addressbook.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {

        authService.register(request);

    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request){

        return authService.login(request);

    }

    @GetMapping("/validate")
    public TokenValidationResponse validate(
            @RequestHeader("Authorization") String header){

        String token = header.replace("Bearer ","");

        String email = jwtService.extractUsername(token);

        return new TokenValidationResponse(true,email);

    }

}