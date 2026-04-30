package com.intellcap.partnerservice.service;

import com.intellcap.partnerservice.dto.*;
import com.intellcap.partnerservice.entity.PartnerRequest;
import com.intellcap.partnerservice.repository.PartnerRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRequestRepository partnerRequestRepository;

    public PartnerFormResponse submit(PartnerFormRequest request) {
        PartnerRequest partnerRequest = PartnerRequest.builder()
                .nom(request.getNom())
                .organisme(request.getOrganisme())
                .pays(request.getPays())
                .ville(request.getVille())
                .email(request.getEmail())
                .whatsapp(request.getWhatsapp())
                .besoin(request.getBesoin())
                .surface(request.getSurface())
                .budget(request.getBudget())
                .ressourcesHumaines(request.getRessourcesHumaines())
                .localisation(request.getLocalisation())
                .build();

        partnerRequestRepository.save(partnerRequest);
        return mapToResponse(partnerRequest);
    }

    public List<PartnerFormResponse> getAllRequests() {
        return partnerRequestRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PartnerFormResponse updateStatus(UUID id, PartnerRequest.Status status) {
        PartnerRequest partnerRequest = partnerRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande introuvable"));
        partnerRequest.setStatus(status);
        partnerRequestRepository.save(partnerRequest);
        return mapToResponse(partnerRequest);
    }

    private PartnerFormResponse mapToResponse(PartnerRequest p) {
        return PartnerFormResponse.builder()
                .id(p.getId())
                .nom(p.getNom())
                .organisme(p.getOrganisme())
                .pays(p.getPays())
                .ville(p.getVille())
                .email(p.getEmail())
                .whatsapp(p.getWhatsapp())
                .besoin(p.getBesoin())
                .surface(p.getSurface())
                .budget(p.getBudget())
                .ressourcesHumaines(p.getRessourcesHumaines())
                .localisation(p.getLocalisation())
                .status(p.getStatus())
                .createdAt(p.getCreatedAt())
                .build();
    }
}