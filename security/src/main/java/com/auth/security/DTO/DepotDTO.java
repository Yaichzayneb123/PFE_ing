package com.auth.security.DTO;

import com.auth.security.Entity.Depot;
import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepotDTO {

    private String codeProduit;


    private int quantity;

    private Integer idEntreprise;

    public static Depot toEntity(DepotDTO dto){
        Depot depot= new Depot();

        depot.setCodeProduit(dto.getCodeProduit());
        depot.setQuantity(dto.getIdEntreprise());
        return depot;
    }

    public static DepotDTO toDTO (Depot depot){
        DepotDTO dto= new DepotDTO();

        dto.setCodeProduit(depot.getCodeProduit());
        dto.setQuantity(depot.getQuantity());
        return dto;
    }

}
