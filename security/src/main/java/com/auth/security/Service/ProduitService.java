package com.auth.security.Service;

import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProduitService {
    Produit save(String pro, MultipartFile file) throws IOException;
    List<Produit> getProduits();
    Produit findById(Integer id);

    Produit updateProduit(Integer id, ProduitDTO produit);

    void Delete (Integer id);


}
