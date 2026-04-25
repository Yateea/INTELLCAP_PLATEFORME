package com.intellcap.userservice.dto;

import lombok.Data;

@Data
public class ProfileRequest {
    private String nom;
    private String prenom;
    private String cvUrl;
    private String linkedinUrl;
    private String telephone;
    private String ville;
    private String pays;
}