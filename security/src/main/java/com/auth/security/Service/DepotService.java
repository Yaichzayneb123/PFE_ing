package com.auth.security.Service;


import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Depot;

import java.util.List;

public interface DepotService {
    DepotDTO save(DepotDTO dto);
List <Depot> GetAll();
    void Delete (Integer id);
    List<ProduitDTO> getProduitByDepotId(Integer id);
    DepotDTO updateDepot (Integer id, DepotDTO depot);
}
