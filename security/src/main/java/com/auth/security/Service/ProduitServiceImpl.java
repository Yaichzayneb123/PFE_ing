package com.auth.security.Service;

import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Categorie;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Depot;
import com.auth.security.Entity.Societe;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.CategorieDAO;
import com.auth.security.Repository.ProduitDAO;
import com.auth.security.Repository.DepotDAO;
import com.auth.security.Repository.SocieteDAO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitDAO produitDAO;

    @Autowired
    private DepotDAO depotDAO;
    @Autowired
    private SocieteDAO societeDAO;

    @Autowired
    private CategorieDAO categorieDAO;
    @Autowired
    ServletContext context;
    private ModelMapper modelMapper = new ModelMapper();

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public ProduitDTO save(String pro, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try {
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }
        ProduitDTO nprod = objectMapper.readValue(pro, ProduitDTO.class);
        nprod.setImage(file.getOriginalFilename());

        Produit produit = modelMapper.map(nprod, Produit.class);
        Optional<Depot> depot= depotDAO.findById(nprod.getDepotId());
        produit.setDepot(depot.get());
        //Optional<Societe> societe= societeDAO.findById(nprod.getSociete());
        //produit.setSociete(societe.get());
        Optional<Categorie> categorie = categorieDAO.findById(nprod.getCategory());
        produit.setCategorie(categorie.get());
        //nprod.setCategoryName(produit.getCategorie().getName());

       Produit savedProduct= produitDAO.save(produit);
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setName(savedProduct.getName());
        produitDTO.setPrice(savedProduct.getPrice());
        produitDTO.setDescription(savedProduct.getDescription());
        produitDTO.setCategory(savedProduct.getCategorie().getId());
        produitDTO.setDepotId(savedProduct.getDepot().getId());
        produitDTO.setImage(savedProduct.getImage());
        produitDTO.setId(savedProduct.getId());
        produitDTO.setQuantity(savedProduct.getQuantity());
        produitDTO.setInventoryStatus(savedProduct.getInventoryStatus());
       // produitDTO.setDepotId(savedProduct.getDepot().getId());
        return produitDTO;
    }

    @Override
    public List<ProduitDTO> getProduits() {
            List<Produit> produits = produitDAO.findAll();
            List<ProduitDTO> customProduits = new ArrayList<>();
            for (Produit produit : produits) {
                ProduitDTO dto = new ProduitDTO();
                //dto.setCategoryName(produit.getCategorie().getName());
                dto.setName(produit.getName());
                dto.setDescription(produit.getDescription());
                dto.setPrice(produit.getPrice());
                dto.setImage(produit.getImage());
                dto.setQuantity(produit.getQuantity());
                dto.setInventoryStatus(produit.getInventoryStatus());
                customProduits.add(dto);
            }
            return customProduits;
        }



    @Override
    public List<Produit> getproductsByIdcategorie (Integer id) {

            Optional<Categorie> categorie = categorieDAO.findById(id);
            if (categorie.isPresent()) {
                return categorie.get().getListProduit();
            } else {
                throw new MyResourceNotFoundException("categorie not found with id " + id);
            }


    }

    @Override
    public Produit findById(Integer id) {
        return produitDAO.findById(id).get();
    }

    @Override
    public ProduitDTO updateProduit(Integer id, ProduitDTO produit) {
        Produit currentProduit = produitDAO.findById(id)
        .orElseThrow(() -> new MyResourceNotFoundException ("produit id not found "+ id));

        Optional<Categorie> categorie = categorieDAO.findById(produit.getCategory());
        if (categorie.isPresent()) {
            currentProduit.setCategorie(categorie.get());
        } else {
            throw new MyResourceNotFoundException("categorie not found with id " + id);
        }
        Optional<Depot> depot = depotDAO.findById(produit.getDepotId());
        if (depot.isPresent()) {
            currentProduit.setDepot(depot.get());
        } else {
            throw new MyResourceNotFoundException("depot not found with id " + id);
        }


            currentProduit.setDescription(produit.getDescription());
            currentProduit.setPrice(produit.getPrice());
            currentProduit.setQuantity(produit.getQuantity());
            currentProduit.setName(produit.getName());
            currentProduit.setInventoryStatus(produit.getInventoryStatus());
            produitDAO.save(currentProduit);

        ProduitDTO updatedProduitDTO = new ProduitDTO();
        updatedProduitDTO.setId(currentProduit.getId());
        updatedProduitDTO.setDescription(currentProduit.getDescription());
        updatedProduitDTO.setPrice(currentProduit.getPrice());
        updatedProduitDTO.setQuantity(currentProduit.getQuantity());
        updatedProduitDTO.setName(currentProduit.getName());
        updatedProduitDTO.setCategory(produit.getCategory());
        updatedProduitDTO.setSociete(produit.getSociete());
        updatedProduitDTO.setImage(produit.getImage());
        updatedProduitDTO.setInventoryStatus(currentProduit.getInventoryStatus());

        return updatedProduitDTO;


        }

    @Override
    public void Delete(Integer id) {
        produitDAO.deleteById(id);
    }
}

