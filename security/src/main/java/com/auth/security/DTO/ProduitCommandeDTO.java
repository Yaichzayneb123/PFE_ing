package com.auth.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProduitCommandeDTO {

    private Integer idProd;
    private Integer quantityCmd;

}
