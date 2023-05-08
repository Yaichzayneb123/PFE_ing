package com.auth.security.controller;

import com.auth.security.DTO.OptionDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.DTO.SocieteDTO;
import com.auth.security.Entity.Option;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Societe;
import com.auth.security.Service.OptionServiceImpl;
import com.auth.security.Service.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/option")
public class OptionController {

    @Autowired
    private OptionServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<OptionDTO> save(@RequestBody OptionDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionDTO> updateOption(@PathVariable("id") Integer id, @RequestBody OptionDTO dto) {
        OptionDTO updatedOption = service.update(id, dto);
        return new ResponseEntity<>(updatedOption, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<OptionDTO> findOptionById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @GetMapping("/all")
    public List<OptionDTO> getAll (){
        return service.getAllOptionDTO();
    }

}
