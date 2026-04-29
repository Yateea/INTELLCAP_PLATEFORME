package com.intellcap.applyservice.dto;

import com.intellcap.applyservice.entity.Application;
import lombok.Data;

@Data
public class StatusRequest {
    private Application.Status status;
    private String hrComment;
}