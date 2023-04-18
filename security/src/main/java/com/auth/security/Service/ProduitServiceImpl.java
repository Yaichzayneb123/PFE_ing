package com.auth.security.Service;

import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Depot;
import com.auth.security.Entity.Societe;
import com.auth.security.Exception.MyResourceNotFoundException;
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
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitDAO produitDAO;
    @Autowired
    private SocieteDAO societeDAO;
    @Autowired
    ServletContext context;
    private ModelMapper modelMapper = new ModelMapper();

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public Produit save(String pro, MultipartFile file) throws IOException {
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
        // Optional<Societe> soc= societeDAO.findById(users.getSociete().getId());
        //Optional<Depot> soc= depotDAO.findById(dto.getIdStock());
        //produit.setDepot(soc.get());
        Optional<Societe> societe= societeDAO.findById(nprod.getSociete());
        produit.setSociete(societe.get());
        produitDAO.save(produit);
        return produit;
    }

    @Override
    public List<Produit> getProduits() {
        return produitDAO.findAll();
    }

    @Override
    public Produit findById(Integer id) {
        return produitDAO.findById(id).get();
    }

    @Override
    public Produit updateProduit(Integer id, ProduitDTO produit) {
        Produit currentProduit = produitDAO.findById(id)
        .orElseThrow(() -> new MyResourceNotFoundException ("produit id not found "+ id));


            currentProduit.setDescription(produit.getDescription());
            currentProduit.setPrice(produit.getPrice());
            currentProduit.setQuantity(produit.getQuantity());
            currentProduit.setCategory(produit.getCategory());
            currentProduit.setQuantity(produit.getQuantity());
            currentProduit.setName(produit.getName());
            currentProduit.setInventoryStatus(produit.getInventoryStatus());

            return produitDAO.save(currentProduit);
        }

    @Override
    public void Delete(Integer id) {
        produitDAO.deleteById(id);
    }
}

