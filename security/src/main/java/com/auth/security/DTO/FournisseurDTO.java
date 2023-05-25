package com.auth.security.DTO;

import com.auth.security.Entity.Client;
import com.auth.security.Entity.Fournisseur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class FournisseurDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Integer idEntreprise;

    public static FournisseurDTO fromEntity (Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }
        return FournisseurDTO.builder()
                .id(fournisseur.getId())
                .firstName(fournisseur.getFirstName())
                .lastName(fournisseur.getLastName())
                .address(fournisseur.getAddress())
                .email(fournisseur.getEmail())
                .phone(fournisseur.getPhone())
                .idEntreprise(fournisseur.getIdEntreprise())

                .build();
    }
    public static Fournisseur toEntity(FournisseurDTO dto) {
        if (dto == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(dto.getId());
        fournisseur.setFirstName(dto.getFirstName());
        fournisseur.setLastName(dto.getLastName());
        fournisseur.setAddress(dto.getAddress());
        fournisseur.setEmail(dto.getEmail());
        fournisseur.setPhone(dto.getPhone());
        fournisseur.setIdEntreprise(dto.getIdEntreprise());
        return fournisseur;
    }
}
