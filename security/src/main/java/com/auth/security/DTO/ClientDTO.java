package com.auth.security.DTO;

import com.auth.security.Entity.Client;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Integer idEntreprise;

    public static ClientDTO fromEntity(Client client) {
        if (client == null) {
            return null;
        }
        return ClientDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .address(client.getAddress())
                .email(client.getEmail())
                .phone(client.getPhone())
                .idEntreprise(client.getIdEntreprise())

                .build();
    }



    public static Client toEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setAddress(dto.getAddress());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setIdEntreprise(dto.getIdEntreprise());
        return client;
    }

}
