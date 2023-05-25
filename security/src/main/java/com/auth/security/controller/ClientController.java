package com.auth.security.controller;

import com.auth.security.DTO.ClientDTO;
import com.auth.security.DTO.DepotDTO;
import com.auth.security.Service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    @Autowired
    private ClientServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<ClientDTO> save(
            @RequestBody ClientDTO dto)
    {
        return ResponseEntity.ok(service.save(dto));
    }
    @GetMapping("/societe/{id}")
    public ResponseEntity<List<ClientDTO>> getClientBySocieteIdId(@PathVariable Integer id) {
        List<ClientDTO> clients = service.getClientsByIdEntreprise(id);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDTO> updateClient (@PathVariable("id") Integer id, @RequestBody ClientDTO dto) {
        ClientDTO updatedClient = service.update( id,dto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
    @DeleteMapping("/del/{id}")
    public void delete(@PathVariable() Integer id) {
        service.Delete( id);
    }

    @GetMapping("/count")
    public int countClients() {
        return service.countClients();
    }
}
