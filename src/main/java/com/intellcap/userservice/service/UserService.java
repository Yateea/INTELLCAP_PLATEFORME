package com.intellcap.userservice.service;

import com.intellcap.userservice.dto.ProfileRequest;
import com.intellcap.userservice.dto.ProfileResponse;
import com.intellcap.userservice.entity.Profile;
import com.intellcap.userservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProfileRepository profileRepository;

    public ProfileResponse createOrUpdateProfile(UUID userId, ProfileRequest request) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElse(Profile.builder().userId(userId).build());

        profile.setNom(request.getNom());
        profile.setPrenom(request.getPrenom());
        profile.setCvUrl(request.getCvUrl());
        profile.setLinkedinUrl(request.getLinkedinUrl());
        profile.setTelephone(request.getTelephone());
        profile.setVille(request.getVille());
        profile.setPays(request.getPays());

        profileRepository.save(profile);
        return mapToResponse(profile);
    }

    public ProfileResponse getProfile(UUID userId) {
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profil introuvable"));
        return mapToResponse(profile);
    }

    private ProfileResponse mapToResponse(Profile profile) {
        return ProfileResponse.builder()
                .id(profile.getId())
                .userId(profile.getUserId())
                .nom(profile.getNom())
                .prenom(profile.getPrenom())
                .cvUrl(profile.getCvUrl())
                .linkedinUrl(profile.getLinkedinUrl())
                .telephone(profile.getTelephone())
                .ville(profile.getVille())
                .pays(profile.getPays())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .build();
    }
}