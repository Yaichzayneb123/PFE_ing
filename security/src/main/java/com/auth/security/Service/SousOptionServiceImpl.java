package com.auth.security.Service;

import com.auth.security.DTO.OptionDTO;
import com.auth.security.DTO.SousOptionDTO;
import com.auth.security.Entity.Option;
import com.auth.security.Entity.SousOption;
import com.auth.security.Repository.SocieteDAO;
import com.auth.security.Repository.SousOptionDAO;
import io.jsonwebtoken.io.IOException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@AllArgsConstructor
@Transactional
@Service
public class SousOptionServiceImpl implements SousOptionService {
    @Autowired
    private SousOptionDAO sousOptionDAO;
    private ModelMapper modelMapper;

    @Override
    public SousOptionDTO save(SousOptionDTO dto) throws IOException {

        return null;
    }

    @Override
    public SousOptionDTO findById(Integer id) {
        SousOption sousOption = sousOptionDAO.findById(id).get();
        SousOptionDTO dto =modelMapper.map(sousOption, SousOptionDTO.class);
        return dto;
    }


    @Override
    public void Delete(Integer id) {

    }
}
