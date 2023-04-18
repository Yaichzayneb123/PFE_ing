package com.auth.security.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GestionnaireDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String country;
    private Integer societe;

}
