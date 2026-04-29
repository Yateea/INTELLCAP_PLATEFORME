package com.intellcap.applyservice.repository;

import com.intellcap.applyservice.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    
    // Vérification anti-doublon (cahier des charges)
    boolean existsByUserIdAndJobId(UUID userId, UUID jobId);
    
    // Candidatures du candidat
    List<Application> findByUserId(UUID userId);
    
    // Candidatures par offre (pour RH)
    List<Application> findByJobId(UUID jobId);
    
    Optional<Application> findByUserIdAndJobId(UUID userId, UUID jobId);
}