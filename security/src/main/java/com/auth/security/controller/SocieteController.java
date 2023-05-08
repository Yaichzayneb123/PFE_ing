package com.auth.security.controller;

import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.DTO.SocieteDTO;
import com.auth.security.Entity.Gestionnaire;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Societe;
import com.auth.security.Entity.Depot;
import com.auth.security.Service.SocieteServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/entreprise")
public class SocieteController {
    @Autowired
    private SocieteServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<Societe> register(
            @RequestBody SocieteDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }
    @GetMapping("/depot/{id}")
    public ResponseEntity<List<DepotDTO>> getDepotBySocieteId(@PathVariable Integer id) {
        List<DepotDTO> depots = service.getDepotBySocieteId(id);
        return new ResponseEntity<>(depots, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Societe> getAll (){
        return service.findAll();
    }

    @GetMapping("/gest/{id}")
    public List<Gestionnaire> getGestionnaire (@PathVariable Integer id){

        return service.getGestionnaire(id);
    }

    @GetMapping("/produit/{id}")
    public List<ProduitDTO> getProduit (@PathVariable Integer id){
        return service.getProduit(id);
    }




}
