package com.auth.security.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CmdFourDTO {
    private Integer id;

    private String code ;

    private LocalDateTime dateCommande;
    private Integer montant;
    private Integer idEntreprise;
    private String Fournisseur;
    private Integer idFour;
    private List<ProduitDTO> produitList;
    private List<Integer> listProdId;
    private List<ProduitCommandeDTO> listcmdDto;
}
