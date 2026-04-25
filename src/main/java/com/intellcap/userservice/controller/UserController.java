package com.intellcap.userservice.controller;

import com.intellcap.userservice.dto.ProfileRequest;
import com.intellcap.userservice.dto.ProfileResponse;
import com.intellcap.userservice.security.JwtUtil;
import com.intellcap.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("User Service is running!");
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileResponse> createOrUpdateProfile(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody ProfileRequest request) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        UUID userId = UUID.nameUUIDFromBytes(email.getBytes());

        return ResponseEntity.ok(userService.createOrUpdateProfile(userId, request));
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getProfile(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        UUID userId = UUID.nameUUIDFromBytes(email.getBytes());

        return ResponseEntity.ok(userService.getProfile(userId));
    }
}