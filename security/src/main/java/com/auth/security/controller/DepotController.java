package com.auth.security.controller;


import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.GestionnaireDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Depot;
import com.auth.security.Entity.Gestionnaire;
import com.auth.security.Service.DepotServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/depot")
public class DepotController {
    @Autowired

    private DepotServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<DepotDTO> save(
            @RequestBody DepotDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable() Integer id) {
        service.Delete(id);
    }

    @GetMapping("/depots")
    public ResponseEntity<List<Depot>> getAll() {
        return ResponseEntity.ok(service.GetAll());
    }


    @GetMapping("/produit/{id}")
    public ResponseEntity<List<ProduitDTO>> getPoduitByDepotId(@PathVariable Integer id) {
        List<ProduitDTO> prods = service.getProduitByDepotId(id);
        return new ResponseEntity<>(prods, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DepotDTO> updateDepot (@PathVariable("id") Integer id, @RequestBody DepotDTO dto) {
        DepotDTO updatedDepot = service.updateDepot(id, dto);
        return new ResponseEntity<>(updatedDepot, HttpStatus.OK);
    }

}
