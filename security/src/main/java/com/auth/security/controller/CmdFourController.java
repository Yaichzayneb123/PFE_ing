package com.auth.security.controller;

import com.auth.security.DTO.CmdClientDTO;
import com.auth.security.DTO.CmdFourDTO;
import com.auth.security.Service.CmdClientServiceImpl;
import com.auth.security.Service.CmdFourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/commandeFour")
public class CmdFourController {

    @Autowired
    private CmdFourServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<CmdFourDTO> save(
            @RequestBody CmdFourDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }
}
