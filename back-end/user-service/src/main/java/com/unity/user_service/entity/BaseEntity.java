package com.unity.user_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // Enables automatic timestamp updates
@Getter @Setter @ToString
public abstract class BaseEntity {  // Making it abstract so it's not instantiated directly

    @CreatedDate
    @Column(updatable = false, nullable = false) // Ensures creation timestamp is never updated
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false, nullable = false) // Ensures update timestamp is auto-generated
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean isDeleted = false; // Soft delete flag
}
