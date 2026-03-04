package com.example.task1.controller;

import com.example.task1.dto.AuthRequest;
import com.example.task1.dto.AuthResponse;
import com.example.task1.service.JwtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.example.task1.dto.*;
import com.example.task1.model.User;
import com.example.task1.repository.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "bearerAuth")

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequestDto request) {

        var claims = jwtService.parse(request.getRefreshToken());

        if (!"REFRESH".equals(claims.get("type"))) {
            throw new RuntimeException("Invalid refresh token");
        }

        String username = claims.getSubject();

        User user = userRepository
                .findByUsername(username)
                .orElseThrow();

        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(newAccessToken, newRefreshToken);
    }
}