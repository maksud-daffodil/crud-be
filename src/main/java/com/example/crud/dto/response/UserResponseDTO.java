package com.example.crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO extends MetadataResponseDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String email;

    private Boolean isActive;
    private Boolean isDeleted;

    public String getFullname() {
        return firstName + lastName;
    }
}
