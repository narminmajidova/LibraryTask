package com.example.task1.controller;

import com.example.task1.dto.AuthRequest;
import com.example.task1.dto.AuthResponse;
import com.example.task1.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "bearerAuth")

public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController( AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        authenticationService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }
}