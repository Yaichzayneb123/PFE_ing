package com.auth.security.Service;

import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Categorie;
import com.auth.security.Entity.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProduitService {
    ProduitDTO save(String pro, MultipartFile file) throws IOException;
    List<ProduitDTO> getProduits();
    Produit findById(Integer id);

    ProduitDTO updateProduit(Integer id, ProduitDTO produit);

    void Delete (Integer id);

    List<Produit> getproductsByIdcategorie(Integer id);
    int countProds();


}
