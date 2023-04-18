package com.auth.security.Service;

import com.auth.security.DTO.CategorieDTO;
import com.auth.security.Entity.Categorie;
import com.auth.security.Entity.Variant;
import com.auth.security.Repository.CategorieDAO;

import java.util.List;

public interface CategorieService {

    Categorie save(CategorieDTO dto);
}
