package com.auth.security.Service;

import com.auth.security.DTO.CategorieDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Categorie;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Variant;
import com.auth.security.Repository.CategorieDAO;

import java.util.List;

public interface CategorieService {

    Categorie save(CategorieDTO dto);
    Categorie findByIdCat(Integer id);
    List<Categorie> getCategories();
    Categorie updateCategorie(Integer id, CategorieDTO categorie);

    void Delete (Integer id);
}
