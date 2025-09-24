package com.example.crud.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Metadata {
    @Builder.Default
    protected Boolean isDeleted = false;

    @CreatedDate
    @Column(
            updatable = false,
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(
            nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    protected LocalDateTime lastModifiedAt = LocalDateTime.now();

//    @ManyToOne
//    @JoinColumn(name = "created_by")
//    protected Person createdBy;
//
//    @ManyToOne
//    @JoinColumn(name = "last_modified_by")
//    protected Person lastModifiedBy;
}
