package com.intellcap.partnerservice.controller;

import com.intellcap.partnerservice.dto.*;
import com.intellcap.partnerservice.entity.PartnerRequest;
import com.intellcap.partnerservice.service.PartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Partner Service is running!");
    }

    // Public — formulaire partenaire (cahier des charges)
    @PostMapping("/submit")
    public ResponseEntity<PartnerFormResponse> submit(
            @Valid @RequestBody PartnerFormRequest request) {
        return ResponseEntity.ok(partnerService.submit(request));
    }

    // Admin — voir toutes les demandes
    @GetMapping("/all")
    public ResponseEntity<List<PartnerFormResponse>> getAllRequests() {
        return ResponseEntity.ok(partnerService.getAllRequests());
    }

    // Admin — changer statut
    @PutMapping("/{id}/status")
    public ResponseEntity<PartnerFormResponse> updateStatus(
            @PathVariable UUID id,
            @RequestParam PartnerRequest.Status status) {
        return ResponseEntity.ok(partnerService.updateStatus(id, status));
    }
}