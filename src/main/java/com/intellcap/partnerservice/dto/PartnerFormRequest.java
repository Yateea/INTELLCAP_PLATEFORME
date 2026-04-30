package com.intellcap.partnerservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PartnerFormRequest {

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "L'organisme est obligatoire")
    private String organisme;

    @NotBlank(message = "Le pays est obligatoire")
    private String pays;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @NotBlank(message = "Le WhatsApp est obligatoire")
    private String whatsapp;

    @Size(max = 1000, message = "Le besoin ne peut pas dépasser 1000 caractères")
    private String besoin;

    // Optionnels
    private Integer surface;
    private Integer budget;
    private Integer ressourcesHumaines;
    private String localisation;
}