package com.auth.security.Service;

import com.auth.security.DTO.CategorieDTO;
import com.auth.security.Entity.*;
import com.auth.security.Repository.CategorieDAO;
import com.auth.security.Repository.VariantDAO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private ModelMapper modelMapper;
    @Autowired
    private CategorieDAO categorieDAO;
    @Autowired
    private  VariantDAO variantDAO;


    @Override
    public Categorie save (CategorieDTO dto) {
       // Categorie categorie = modelMapper.map(dto, Categorie.class);
        Categorie categorie = new Categorie();
        categorie.setName(dto.getName());
        categorie.setDesignation(dto.getDesignation());
        //categorie.setName(dto.getName());
        categorieDAO.save(categorie);
        return categorie;
    }
    /*public Categorie save(CategorieDTO dto) {
        Categorie categorie = modelMapper.map(dto, Categorie.class);
        Variant variant = modelMapper.map(variantDTO, Variant.class);
        categorie.getVariantList().add(variant);
        categorieDAO.save(categorie);
        return categorie;
    }*/


}
