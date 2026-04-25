package com.intellcap.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private UUID id;
    private UUID userId;
    private String nom;
    private String prenom;
    private String cvUrl;
    private String linkedinUrl;
    private String telephone;
    private String ville;
    private String pays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}