package com.auth.security.Service;

import com.auth.security.DTO.GestionnaireDTO;
import com.auth.security.DTO.OptionDTO;
import com.auth.security.Entity.Gestionnaire;
import com.auth.security.Entity.Option;
import io.jsonwebtoken.io.IOException;

import java.util.List;

public interface OptionService {

    OptionDTO save(OptionDTO option) throws IOException;
    OptionDTO findById(Integer id);
    OptionDTO update(Integer id, OptionDTO option);

    void Delete (Integer id);
    List<OptionDTO> getAllOptionDTO();
}
