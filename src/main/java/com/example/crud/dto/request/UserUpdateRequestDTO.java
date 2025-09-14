package com.example.crud.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequestDTO {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String email;
    private Boolean isActive;
}
