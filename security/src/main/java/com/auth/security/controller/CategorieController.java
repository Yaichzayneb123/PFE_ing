package com.auth.security.controller;

import com.auth.security.DTO.CategorieDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Categorie;
import com.auth.security.Entity.Produit;
import com.auth.security.Service.CategorieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cat")
public class CategorieController {
    @Autowired
    private CategorieServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Categorie> save(
            @RequestBody CategorieDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Categorie>> getAllCategorie(){
        return ResponseEntity.ok().body(service.getCategories());
    }

    @PutMapping("/{id}") public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") Integer id, @RequestBody CategorieDTO categorie) {
        Categorie updatedCategorie = service.updateCategorie(id, categorie);
        return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Categorie> findByIdcatego(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(service.findByIdCat(id));
    }
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable() Integer id) {
        service.Delete(id);
    }

}
