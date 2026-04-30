package com.intellcap.partnerservice.repository;

import com.intellcap.partnerservice.entity.PartnerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PartnerRequestRepository extends JpaRepository<PartnerRequest, UUID> {
    boolean existsByEmail(String email);
    List<PartnerRequest> findByStatus(PartnerRequest.Status status);
}