package com.auth.security.Service;

import com.auth.security.DTO.*;
import com.auth.security.Entity.Gestionnaire;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Societe;
import com.auth.security.Entity.Depot;

import java.util.List;

public interface SocieteService {
    Societe save(SocieteDTO dto);

    SocieteDTO findById(Integer id);
    List<Societe> findAll();
    void delete (Integer id);

    List<DepotDTO> getDepotBySocieteId(Integer id);

    List<Gestionnaire> getGestionnaire (Integer Id);
    List<ProduitDTO> getProduit (Integer Id);
    List<CmdClientDTO> getCommandeByIdSociete(Integer id);
    List<CmdFourDTO> getCommandeFourByIdSociete(Integer id);
    public SocieteDTO validateSociete(Integer id);

    public List<SocieteDTO> getAll();

}
