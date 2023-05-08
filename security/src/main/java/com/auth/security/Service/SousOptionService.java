package com.auth.security.Service;

import com.auth.security.DTO.OptionDTO;
import com.auth.security.DTO.SousOptionDTO;
import io.jsonwebtoken.io.IOException;

public interface SousOptionService {
    SousOptionDTO save(SousOptionDTO dto) throws IOException;
    SousOptionDTO findById(Integer id);

    void Delete (Integer id);
}
