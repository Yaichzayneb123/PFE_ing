package com.auth.security.controller;

import com.auth.security.DTO.AuthenticationRequest;
import com.auth.security.DTO.AuthenticationResponse;
import com.auth.security.DTO.SocieteDTO;
import com.auth.security.Service.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<SocieteDTO> register(
            @RequestBody SocieteDTO request
            ){
        return ResponseEntity.ok(service.register(request));

    }


    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }

}
