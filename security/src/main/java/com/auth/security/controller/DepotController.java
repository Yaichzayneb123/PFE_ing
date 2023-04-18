package com.auth.security.controller;


import com.auth.security.DTO.DepotDTO;
import com.auth.security.Entity.Depot;
import com.auth.security.Service.DepotServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class DepotController {
    @Autowired

    private DepotServiceImpl service;



    @PostMapping("/save")
    public ResponseEntity<Depot> save(
            @RequestBody DepotDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));


    }
    @GetMapping("/stocks")
    public ResponseEntity<List<Depot>> getAll() {
        return ResponseEntity.ok(service.GetAll());
    }

}
