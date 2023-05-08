package com.auth.security.DTO;

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
    private String price;
    private String image;
    private String quantity;
    private Integer category;
   private Integer depotId;
    private String inventoryStatus;
    private Integer societe;

}
