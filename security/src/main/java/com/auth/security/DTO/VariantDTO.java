package com.auth.security.DTO;

import lombok.Builder;
import lombok.Data;

@Data

@Builder
public class VariantDTO {
    private String taille;
    private String color;
    private Integer idproduit;
    private Integer idcategorie;



}
