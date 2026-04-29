package com.intellcap.applyservice.controller;

import com.intellcap.applyservice.dto.*;
import com.intellcap.applyservice.security.JwtUtil;
import com.intellcap.applyservice.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;
    private final JwtUtil jwtUtil;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Apply Service is running!");
    }

    // Candidat — postuler à une offre
    @PostMapping
    public ResponseEntity<ApplicationResponse> apply(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody ApplicationRequest request) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        UUID userId = UUID.nameUUIDFromBytes(email.getBytes());

        return ResponseEntity.ok(applicationService.apply(userId, request));
    }

    // Candidat — voir ses candidatures (SANS commentaire RH)
    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> getMyCandidatures(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String email = jwtUtil.extractEmail(token);
        UUID userId = UUID.nameUUIDFromBytes(email.getBytes());

        return ResponseEntity.ok(applicationService.getMyCandidatures(userId));
    }

    // RH/Admin — voir candidatures par offre (AVEC commentaire RH)
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationAdminResponse>> getCandidaturesByJob(
            @PathVariable UUID jobId) {
        return ResponseEntity.ok(applicationService.getCandidaturesByJob(jobId));
    }

    // RH/Admin — changer statut + commentaire invisible candidat
    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationAdminResponse> updateStatus(
            @PathVariable UUID id,
            @RequestBody StatusRequest request) {
        return ResponseEntity.ok(applicationService.updateStatus(id, request));
    }
}