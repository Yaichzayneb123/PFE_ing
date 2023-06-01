package com.auth.security.DTO;

import com.auth.security.Entity.Categorie;
import com.auth.security.Entity.Depot;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Societe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProduitDTO {
    private Integer Id;
    private String name;
    private String description;
    private Integer price;
    private String image;
    private Integer quantity;
    private Integer category;
    private String categoryName;
   private Integer depotId;
    private String inventoryStatus;



    public static ProduitDTO fromEntity(Produit entity) {
        if (entity == null) {
            return null;
        }

        ProduitDTO dto = new ProduitDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setImage(entity.getImage());
        dto.setQuantity(entity.getQuantity());
        dto.setCategory(entity.getCategorie().getId());
        dto.setDepotId(entity.getDepot().getId());
        dto.setInventoryStatus(entity.getInventoryStatus());
        //dto.setCategoryName(entity.getCategorie().getName()); // Ajout du nom de catégorie

        return dto;
    }



    public static Produit toEntity(ProduitDTO dto) {
        Produit entity = new Produit();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImage(dto.getImage());
        entity.setQuantity(dto.getQuantity());
        entity.setInventoryStatus(dto.getInventoryStatus());
        Depot depot = new Depot();
        depot.setId(dto.getDepotId());
        entity.setDepot(depot);
        Categorie categorie = new Categorie();
        categorie.setId(dto.getCategory());
        entity.setCategorie(categorie);
        //Societe societe = new Societe();
        //societe.setId(dto.getSociete());
        //entity.setSociete(societe);
        return entity;
    }


}
