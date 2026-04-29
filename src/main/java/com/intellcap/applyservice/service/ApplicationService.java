package com.intellcap.applyservice.service;

import com.intellcap.applyservice.dto.*;
import com.intellcap.applyservice.entity.Application;
import com.intellcap.applyservice.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationResponse apply(UUID userId, ApplicationRequest request) {
        // Vérification anti-doublon (cahier des charges)
        if (applicationRepository.existsByUserIdAndJobId(userId, request.getJobId())) {
            throw new RuntimeException("Vous avez déjà postulé à cette offre");
        }

        Application application = Application.builder()
                .userId(userId)
                .jobId(request.getJobId())
                .coverLetter(request.getCoverLetter())
                .build();

        applicationRepository.save(application);
        return mapToResponse(application);
    }

    // Candidat — voit ses candidatures SANS commentaire RH
    public List<ApplicationResponse> getMyCandidatures(UUID userId) {
        return applicationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // RH/Admin — voit toutes les candidatures AVEC commentaire RH
    public List<ApplicationAdminResponse> getCandidaturesByJob(UUID jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::mapToAdminResponse)
                .collect(Collectors.toList());
    }

    // RH/Admin — change statut + ajoute commentaire invisible candidat
    public ApplicationAdminResponse updateStatus(UUID id, StatusRequest request) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature introuvable"));

        application.setStatus(request.getStatus());

        if (request.getHrComment() != null) {
            application.setHrComment(request.getHrComment());
        }

        applicationRepository.save(application);
        return mapToAdminResponse(application);
    }

    // Mapping candidat — SANS hrComment
    private ApplicationResponse mapToResponse(Application app) {
        return ApplicationResponse.builder()
                .id(app.getId())
                .userId(app.getUserId())
                .jobId(app.getJobId())
                .coverLetter(app.getCoverLetter())
                .status(app.getStatus())
                .appliedAt(app.getAppliedAt())
                .updatedAt(app.getUpdatedAt())
                .build();
    }

    // Mapping admin — AVEC hrComment
    private ApplicationAdminResponse mapToAdminResponse(Application app) {
        return ApplicationAdminResponse.builder()
                .id(app.getId())
                .userId(app.getUserId())
                .jobId(app.getJobId())
                .coverLetter(app.getCoverLetter())
                .status(app.getStatus())
                .hrComment(app.getHrComment())
                .appliedAt(app.getAppliedAt())
                .updatedAt(app.getUpdatedAt())
                .build();
    }
}