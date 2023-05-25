package com.auth.security.DTO;

import com.auth.security.Entity.Depot;
import com.auth.security.Entity.Produit;
import lombok.*;

import java.util.List;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepotDTO {
    private Integer Id;
    private int quantity;
    private Integer idEntreprise;
    private Integer idGestionnaire;
    private String adresse;
    private String name;



    public static Depot toEntity(DepotDTO dto){
        Depot depot= new Depot();

        depot.setName(dto.getName());
        depot.setAdresse(dto.getAdresse());
        depot.setQuantity(dto.getQuantity());
        //depot.setProduit(depot.getProduit().get());



        return depot;
    }

    public static DepotDTO toDTO (Depot depot){
        DepotDTO dto= new DepotDTO();

        dto.setAdresse(depot.getAdresse());
        dto.setName(depot.getName());
        dto.setQuantity(depot.getQuantity());
        dto.setIdEntreprise(depot.getSociete().getId());
        return dto;


    }


}
