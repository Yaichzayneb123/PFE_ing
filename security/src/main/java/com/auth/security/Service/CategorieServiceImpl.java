package com.auth.security.Service;

import com.auth.security.DTO.CategorieDTO;
import com.auth.security.Entity.*;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.CategorieDAO;
import com.auth.security.Repository.VariantDAO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private ModelMapper modelMapper;
    @Autowired
    private CategorieDAO categorieDAO;



    @Override
    public Categorie save (CategorieDTO dto) {
        Categorie categorie = new Categorie();
        categorie.setName(dto.getName());
        categorie.setDesignation(dto.getDesignation());
        categorie.setDescription(dto.getDescription());
        categorieDAO.save(categorie);
        return categorie;
    }


    @Override
    public Categorie findByIdCat(Integer id) {
        return categorieDAO.findById(id).get();
    }

    @Override
    public List<Categorie> getCategories() {
        return categorieDAO.findAll();
    }

    @Override
    public Categorie updateCategorie(Integer id, CategorieDTO categorie) {
        Categorie currentCategorie = categorieDAO.findById(id)
                .orElseThrow(() -> new MyResourceNotFoundException("produit id not found "+ id));


        currentCategorie.setDescription(categorie.getDescription());
        currentCategorie.setDesignation(categorie.getDesignation());
        currentCategorie.setName(categorie.getName());


        return categorieDAO.save(currentCategorie);
    }

    @Override
    public void Delete(Integer id) {
        categorieDAO.deleteById(id);


    }
}
