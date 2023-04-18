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
    private String name;
    private String description;
    private String price;
    private String image;
    private Integer idStock;
    private String quantity;
    private String category;
    private String inventoryStatus;
    private Integer societe;
}
