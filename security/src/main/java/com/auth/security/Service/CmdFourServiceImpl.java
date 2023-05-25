package com.auth.security.Service;

import com.auth.security.DTO.CmdClientDTO;
import com.auth.security.DTO.CmdFourDTO;
import com.auth.security.DTO.CmdMapperFour;
import com.auth.security.DTO.ProduitCommandeDTO;
import com.auth.security.Entity.CommandeClient;
import com.auth.security.Entity.CommandeFour;
import com.auth.security.Entity.Produit;
import com.auth.security.Repository.CmdFourDAO;
import com.auth.security.Repository.ProduitDAO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class CmdFourServiceImpl implements CmdFourService{

    private CmdMapperFour mapper = new CmdMapperFour();
    @Autowired
    private ProduitDAO produitDAO;
    @Autowired
    private CmdFourDAO cmdFourDAO;

    @Override
    public CmdFourDTO save(CmdFourDTO cmdFourDTO) {
        cmdFourDTO.setDateCommande(LocalDateTime.now());
        CommandeFour commandeFour = mapper.toEntity(cmdFourDTO);
        List<Produit> produitList = new ArrayList<>();
        for (ProduitCommandeDTO produit : cmdFourDTO.getListcmdDto()) {
            Produit existingProduct = produitDAO.findById(produit.getIdProd())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            int newQuantity = existingProduct.getQuantity() + produit.getQuantityCmd();
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

        commandeFour.setProduitList(produitList);
        // List<Produit> produitsCommandes = cmdClientDTO.getProduitList();
        CommandeFour savedCommandeFour = cmdFourDAO.save(commandeFour);

        // créer une nouvelle instance de CmdClientDTO avec la liste des produits commandés
        CmdFourDTO savedCmdFourDTO = new CmdFourDTO();
        savedCmdFourDTO.setId(savedCommandeFour.getId());
        savedCmdFourDTO.setDateCommande(savedCommandeFour.getDateCommande());
        savedCmdFourDTO.setCode(savedCommandeFour.getCode());
        savedCmdFourDTO.setMontant(savedCommandeFour.getMontant());
        //savedCmdClientDTO.setClient(savedCommandeClient.getClient().getFirstName()+" "+savedCommandeClient.getClient().getLastName());
        savedCmdFourDTO.setIdFour(savedCommandeFour.getFournisseur().getId());
        savedCmdFourDTO.setIdEntreprise(savedCommandeFour.getIdEntreprise());
        List<Integer> listIds = cmdFourDTO.getListProdId();
        savedCmdFourDTO.setListProdId(listIds);
        //savedCmdClientDTO.setProduitList(produitsCommandes);

        // retourner le DTO avec la liste des produits commandés
        return savedCmdFourDTO;

    }
}
