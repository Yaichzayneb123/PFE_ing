package com.auth.security.controller;

import com.auth.security.DTO.GestionnaireDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Gestionnaire;
import com.auth.security.Entity.Produit;
import com.auth.security.Repository.GestionnaireDAO;
import com.auth.security.Service.GestionnaireServiceImpl;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gestionnaire")
public class GestionnaireController {
    @Autowired
    private GestionnaireServiceImpl service;




    @PostMapping("/save")
    public Gestionnaire saveGestionnaire(@RequestBody GestionnaireDTO requestBody) throws IOException {
      return service.save(requestBody);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Gestionnaire> updateGset(@PathVariable("id") Integer id, @RequestBody GestionnaireDTO dto) {
        Gestionnaire updatedGest = service.updategest(id, dto);
        return new ResponseEntity<>(updatedGest, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<GestionnaireDTO> findById(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    @GetMapping("/getgest")
    public ResponseEntity<List<Gestionnaire>> getGest(){
        return ResponseEntity.ok().body(service.getAllgest());
    }


    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable() Integer id) {
        service.Delete(id);
    }

    @GetMapping("/count")
    public int countGests() {
        return service.countGests();
    }

}
