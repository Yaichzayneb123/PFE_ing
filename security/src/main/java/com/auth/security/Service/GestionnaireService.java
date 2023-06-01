package com.auth.security.Service;

import com.auth.security.DTO.GestionnaireDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Gestionnaire;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Societe;
import com.auth.security.Entity.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.io.IOException;

import java.util.List;
import java.util.Optional;

public interface GestionnaireService {

    Gestionnaire save(GestionnaireDTO chaine) throws IOException;
    List<Gestionnaire> getAllgest();
    GestionnaireDTO findById(Integer id);
    Gestionnaire updategest(Integer id, GestionnaireDTO dto);
    void Delete (Integer id);
    int countGests();



}
