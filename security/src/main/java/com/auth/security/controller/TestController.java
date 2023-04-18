package com.auth.security.controller;

import com.auth.security.DTO.CategorieDTO;
import com.auth.security.Entity.Categorie;
import com.auth.security.Service.CategorieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
    @Autowired
    private CategorieServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Categorie> save(
            @RequestBody CategorieDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));


    }
}
