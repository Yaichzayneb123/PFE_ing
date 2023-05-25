package com.auth.security.DTO;

import com.auth.security.Entity.Client;
import com.auth.security.Entity.CommandeClient;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Variant;
import com.auth.security.Repository.ProduitDAO;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CmdClientDTO {
    private Integer id;

    private String code ;

    private LocalDateTime dateCommande;
    private Integer montant;
    private Integer idEntreprise;
    private String client;
    private Integer IdClient;
    private List<ProduitDTO> produitList;
    private List<Integer> listProdId;
    private List<ProduitCommandeDTO> listcmdDto;
    //private List<VariantDTO> variantDTO;


}
