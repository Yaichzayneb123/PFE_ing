package com.auth.security.DTO;

import com.auth.security.Entity.Client;
import com.auth.security.Entity.CommandeClient;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.SousOption;
import com.auth.security.Repository.ProduitDAO;
import com.auth.security.Service.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class CmdMapper {
    @Autowired
    private ProduitDAO produitDAO;
    @Autowired
    private ProduitServiceImpl service;

    public static CmdClientDTO fromEntity (CommandeClient commandeClient) {
        if (commandeClient == null) {
            return null;
        }

        List<ProduitDTO> produitsCommandes = new ArrayList<>();
        for (Produit produit : commandeClient.getProduitList()) {
            produitsCommandes.add(ProduitDTO.fromEntity(produit));
        }

        return CmdClientDTO.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .montant(commandeClient.getMontant())
                .client(commandeClient.getClient().getFirstName()+" "+commandeClient.getClient().getLastName())
                .IdClient(commandeClient.getClient().getId())
                .idEntreprise(commandeClient.getIdEntreprise())
                .produitList(produitsCommandes)
                .build();
    }

    public  CommandeClient toEntity(CmdClientDTO dto) {
        CommandeClient entity = new CommandeClient();
        //entity.setId(dto.getId());
        //entity.setCode(dto.getCode());
        entity.setMontant(dto.getMontant());
        entity.setDateCommande(dto.getDateCommande());
        entity.setIdEntreprise(dto.getIdEntreprise());
        Client client = new Client();
        client.setFirstName(dto.getClient());
        client.setId(dto.getIdClient());
        entity.setClient(client);

        List<Produit> produitList = new ArrayList<>();
        for (ProduitDTO produitDTO : dto.getProduitList()) {
            Produit produit = ProduitDTO.toEntity(produitDTO);
            produitList.add(produit);
        }
        //entity.setProduitList(produitList);
        return entity;
    }
}
