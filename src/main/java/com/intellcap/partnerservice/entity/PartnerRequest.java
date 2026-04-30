package com.intellcap.partnerservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "partner_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Infos obligatoires (cahier des charges)
    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String organisme;

    @Column(nullable = false)
    private String pays;

    @Column(nullable = false)
    private String ville;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String whatsapp;

    // Expression de besoin
    @Column(columnDefinition = "TEXT")
    private String besoin;

    // Détails logistiques optionnels
    private Integer surface;
    private Integer budget;
    private Integer ressourcesHumaines;
    private String localisation;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = Status.EN_ATTENTE;
    }

    public enum Status {
        EN_ATTENTE,
        CONTACTE,
        ACCEPTE,
        REJETE
    }
}