package com.auth.security.controller;

import com.auth.security.Repository.UserDAO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserDAO userDAO;


    /*@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public Optional<Users> findByIdSociete(@PathVariable("id") Integer id) {
        return userDAO.findByIdsociete(id);
    }*/
}
