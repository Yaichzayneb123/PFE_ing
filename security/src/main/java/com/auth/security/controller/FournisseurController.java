package com.auth.security.controller;

import com.auth.security.DTO.ClientDTO;
import com.auth.security.DTO.FournisseurDTO;
import com.auth.security.Entity.Fournisseur;
import com.auth.security.Service.FournisseurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fourn")
public class FournisseurController {
    @Autowired
    private FournisseurServiceImpl service;
    @PostMapping("/save")
    public ResponseEntity<FournisseurDTO> save(
            @RequestBody FournisseurDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }
    @GetMapping("/societe/{id}")
    public ResponseEntity<List<FournisseurDTO>> getfournBySocieteIdId(@PathVariable Integer id) {
        List<FournisseurDTO> fournisseurDTOS = service.getFoursByIdEntreprise(id);
        return new ResponseEntity<>(fournisseurDTOS, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<FournisseurDTO> updateFourn (@PathVariable("id") Integer id, @RequestBody FournisseurDTO dto) {
        FournisseurDTO updatedFourn = service.update( id,dto);
        return new ResponseEntity<>(updatedFourn, HttpStatus.OK);
    }
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable() Integer id) {
        service.Delete( id);
    }

    @GetMapping("/count")
    public int countFourn() {
        return service.countFourns();
    }
}
