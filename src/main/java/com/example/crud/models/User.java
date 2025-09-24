package com.example.crud.models;

import com.example.crud.dto.response.UserResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User_Info")
public class User extends Metadata {
    @Id
    private UUID id;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String email;

    @Builder.Default
    private Boolean isActive = false;

    @Transient
    public String getFullname() {
        return firstName + lastName;
    }

    @Transient
    public UserResponseDTO toDTO() {
        return UserResponseDTO.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .phone(phone)
                .email(email)
                .isActive(isActive)
                .isDeleted(isDeleted)
                .build();
    }
}
