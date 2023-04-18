package com.auth.security.Service;


import com.auth.security.DTO.DepotDTO;
import com.auth.security.Entity.Depot;

import java.util.List;

public interface DepotService {
    Depot save(DepotDTO dto);
List <Depot> GetAll();
}
