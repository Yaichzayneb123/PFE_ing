package com.auth.security.Service;

import com.auth.security.DTO.ClientDTO;
import com.auth.security.DTO.FournisseurDTO;

import java.util.List;

public interface FournisseurService {
    FournisseurDTO save(FournisseurDTO fournisseurDTO) ;
    List<FournisseurDTO> getFoursByIdEntreprise(Integer idEntreprise);
    void Delete (Integer id);
    FournisseurDTO update(Integer id, FournisseurDTO fournisseurDTO) ;
    int countFourns();
}
