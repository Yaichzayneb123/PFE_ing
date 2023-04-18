package com.auth.security.controller;

import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Produit;
import com.auth.security.Repository.ProduitDAO;
import com.auth.security.Service.ProduitServiceImpl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

@RestController
@RequestMapping("/api/v1/produit")
public class ProduitController {
@Autowired
    private ProduitServiceImpl service;
@Autowired
    ServletContext context;
@Autowired private ProduitDAO produitDAO;
    private ModelMapper modelMapper = new ModelMapper();
    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @GetMapping("/getproduit")
    public ResponseEntity<List<Produit>> getProduits(){
        return ResponseEntity.ok().body(service.getProduits());
    }


    @PostMapping(value="/Add", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Produit> addEvent(@RequestPart("produit") String pro, @RequestPart("image") MultipartFile file) throws IOException {

        Produit produit= service.save(pro,file);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("id") Integer id, @RequestBody ProduitDTO produit) {
        Produit updatedProduit = service.updateProduit(id, produit);
        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Produit> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable() Integer id) {
        service.Delete(id);
    }



}
