package com.intellcap.partnerservice.dto;

import com.intellcap.partnerservice.entity.PartnerRequest;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnerFormResponse {
    private UUID id;
    private String nom;
    private String organisme;
    private String pays;
    private String ville;
    private String email;
    private String whatsapp;
    private String besoin;
    private Integer surface;
    private Integer budget;
    private Integer ressourcesHumaines;
    private String localisation;
    private PartnerRequest.Status status;
    private LocalDateTime createdAt;
}