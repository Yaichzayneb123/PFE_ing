package com.auth.security.controller;

import com.auth.security.DTO.CmdClientDTO;
import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.ProduitCommandeDTO;
import com.auth.security.Service.CmdClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/commandes")
public class CmdClientController {

        @Autowired
        private CmdClientServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<CmdClientDTO> save(
            @RequestBody CmdClientDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }
    }

