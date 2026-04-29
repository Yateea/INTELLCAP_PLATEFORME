package com.intellcap.applyservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class ApplicationRequest {

    @NotNull
    private UUID jobId;

    private String coverLetter;
}