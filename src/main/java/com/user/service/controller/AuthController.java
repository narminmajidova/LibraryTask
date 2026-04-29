package com.user.service.controller;

import com.user.service.dto.AuthRequest;
import com.user.service.dto.AuthResponse;
import com.user.service.dto.RefreshRequestDto;
import com.user.service.dto.UserDto;
import com.user.service.model.User;
import com.user.service.repository.UserRepository;
import com.user.service.service.JwtService;
import com.user.service.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserServiceImpl userServiceImpl;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          JwtService jwtService, UserServiceImpl userServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto request) {
        UserDto created = userServiceImpl.createUser(request);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity.status(403)
                    .body("AUTH ERROR: " + e.getClass().getSimpleName() + " → " + e.getMessage());
        }

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
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