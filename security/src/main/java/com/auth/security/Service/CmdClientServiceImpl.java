package com.auth.security.Service;

import com.auth.security.DTO.CmdClientDTO;
import com.auth.security.DTO.CmdMapper;
import com.auth.security.DTO.ProduitCommandeDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.*;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class CmdClientServiceImpl implements CmdClientService {
    @Autowired

    private ProduitDAO produitDAO;
    @Autowired

    private SocieteDAO societeDAO;
    @Autowired
    private CmdClientDAO cmdClientDAO;

    private CmdMapper cmdMapper = new CmdMapper();

    @Override
    public CmdClientDTO save(CmdClientDTO cmdClientDTO) {
        cmdClientDTO.setDateCommande(LocalDateTime.now());
        CommandeClient commandeClient = cmdMapper.toEntity(cmdClientDTO);
        List<Produit> produitList = new ArrayList<>();
        for (ProduitCommandeDTO produit : cmdClientDTO.getListcmdDto()) {
            Produit existingProduct = produitDAO.findById(produit.getIdProd())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            int newQuantity = existingProduct.getQuantity() - produit.getQuantityCmd();
            if (newQuantity >=0) {

                if (newQuantity == 0) {
                    existingProduct.setInventoryStatus("OUTOFSTOCK");
                }
                else if (newQuantity <= 5) {
                    existingProduct.setInventoryStatus("LOWSTOCK");
                }
                existingProduct.setQuantityCmd(produit.getQuantityCmd());
                existingProduct.setQuantity(newQuantity);
                produitDAO.save(existingProduct);
                produitList.add(existingProduct);
            }
        }

        commandeClient.setProduitList(produitList);
       // List<Produit> produitsCommandes = cmdClientDTO.getProduitList();
        CommandeClient savedCommandeClient = cmdClientDAO.save(commandeClient);

        // créer une nouvelle instance de CmdClientDTO avec la liste des produits commandés
        CmdClientDTO savedCmdClientDTO = new CmdClientDTO();
        savedCmdClientDTO.setId(savedCommandeClient.getId());
        savedCmdClientDTO.setDateCommande(savedCommandeClient.getDateCommande());
        savedCmdClientDTO.setCode(savedCommandeClient.getCode());
        savedCmdClientDTO.setMontant(savedCommandeClient.getMontant());
        //savedCmdClientDTO.setClient(savedCommandeClient.getClient().getFirstName()+" "+savedCommandeClient.getClient().getLastName());
        savedCmdClientDTO.setIdClient(savedCommandeClient.getClient().getId());
        savedCmdClientDTO.setIdEntreprise(savedCommandeClient.getIdEntreprise());
        List<Integer> listIds = cmdClientDTO.getListProdId();
        savedCmdClientDTO.setListProdId(listIds);
        //savedCmdClientDTO.setProduitList(produitsCommandes);


        return savedCmdClientDTO;
    }





}



