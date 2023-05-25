package com.auth.security.DTO;

import com.auth.security.Entity.*;
import com.auth.security.Repository.FournisseurDAO;
import com.auth.security.Service.FournisseurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CmdMapperFour {
    @Autowired
    private FournisseurDAO fourDAO;
    @Autowired
    private FournisseurServiceImpl service;

    public static CmdFourDTO fromEntity (CommandeFour commandeFour) {
        if (commandeFour == null) {
            return null;
        }

        List<ProduitDTO> produitsCommandes = new ArrayList<>();
        for (Produit produit : commandeFour.getProduitList()) {
            produitsCommandes.add(ProduitDTO.fromEntity(produit));
        }

        return CmdFourDTO.builder()
                .id(commandeFour.getId())
                .code(commandeFour.getCode())
                .dateCommande(commandeFour.getDateCommande())
                .montant(commandeFour.getMontant())
                //.fournisseur(commandeFour.getClient().getFirstName()+" "+commandeClient.getClient().getLastName())
                .idFour(commandeFour.getFournisseur().getId())
                .idEntreprise(commandeFour.getIdEntreprise())
                .produitList(produitsCommandes)
                .build();
    }

    public  CommandeFour toEntity(CmdFourDTO dto) {
        CommandeFour entity = new CommandeFour();
        //entity.setId(dto.getId());
        //entity.setCode(dto.getCode());
        entity.setMontant(dto.getMontant());
        entity.setDateCommande(dto.getDateCommande());
        entity.setIdEntreprise(dto.getIdEntreprise());
        Fournisseur four = new Fournisseur();
        four.setFirstName(dto.getFournisseur());
        four.setId(dto.getIdFour());
        entity.setFournisseur(four);

        List<Produit> produitList = new ArrayList<>();
        for (ProduitDTO produitDTO : dto.getProduitList()) {
            Produit produit = ProduitDTO.toEntity(produitDTO);
            produitList.add(produit);
        }
        //entity.setProduitList(produitList);
        return entity;
    }
}
