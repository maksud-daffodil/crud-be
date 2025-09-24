package com.example.crud.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserCreationRequestDTO {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String phone;
    private String email;
}
