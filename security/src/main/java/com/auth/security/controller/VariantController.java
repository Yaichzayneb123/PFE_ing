package com.auth.security.controller;

import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.DTO.VariantDTO;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Variant;
import com.auth.security.Service.VariantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/variant")
public class VariantController {
    @Autowired
    private VariantServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<VariantDTO> save(
            @RequestBody VariantDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Variant> updatevariant(@PathVariable("id") Integer id, @RequestBody VariantDTO variant) {
        Variant updatedProduit = service.updatevariant(id, variant);
        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
    }

    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable() Integer id) {
        service.Delete(id);
    }

    @GetMapping("/getvariant")
    public ResponseEntity<List<Variant>> getVariants(){
        return ResponseEntity.ok().body(service.getAllVariants());
    }

    @GetMapping("/getvariantByIdProduit/{id}")
    public ResponseEntity<List<VariantDTO>> getVariantsByIdProd(@PathVariable Integer id){
        List<VariantDTO> variants = service.getVariantsByIdProduit(id);
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }



}
