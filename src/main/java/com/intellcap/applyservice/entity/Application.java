package com.intellcap.applyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private UUID jobId;

    private String coverLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Commentaire RH — invisible pour le candidat
    @Column(name = "hr_comment")
    private String hrComment;

    @Column(nullable = false, updatable = false)
    private LocalDateTime appliedAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.appliedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = Status.EN_ATTENTE;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum Status {
        EN_ATTENTE,
        EN_REVUE,
        FINALISEE,
        REJETEE
    }
}