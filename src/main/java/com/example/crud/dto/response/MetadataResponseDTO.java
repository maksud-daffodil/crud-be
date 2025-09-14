package com.example.crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class MetadataResponseDTO {
    private Boolean isDeleted;
    private String createdAt;
    private String lastModifiedAt;
//    private PersonDTO createdBy;
//    private PersonDTO lastModifiedBy;
}
