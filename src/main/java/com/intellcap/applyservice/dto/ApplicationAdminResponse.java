package com.intellcap.applyservice.dto;

import com.intellcap.applyservice.entity.Application;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationAdminResponse {
    private UUID id;
    private UUID userId;
    private UUID jobId;
    private String coverLetter;
    private Application.Status status;
    private String hrComment; // visible uniquement pour RH/ADMIN
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;
}