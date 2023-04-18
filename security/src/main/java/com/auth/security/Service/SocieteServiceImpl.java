package com.auth.security.Service;

import com.auth.security.DTO.SocieteDTO;
import com.auth.security.Entity.*;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.SocieteDAO;
import com.auth.security.Repository.UserDAO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@AllArgsConstructor
@Transactional
@Service
public class SocieteServiceImpl implements SocieteService {

        @Autowired
private SocieteDAO societeDAO;
private ModelMapper modelMapper = new ModelMapper();
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private UserDAO userDAO;



@Override
public Societe save(SocieteDTO dto) {
        Societe societe = modelMapper.map(dto, Societe.class);
        societeDAO.save(societe);
        Users users = UserMapper.toUser(societe);
        users.setPassword(passwordEncoder.encode(societe.getPassword()));
        userDAO.save(users);

        return societe;
        }

@Override
public SocieteDTO findById(Integer id) {
        Optional<Societe> societe = societeDAO.findById(id);
        return modelMapper.map(societe, SocieteDTO.class);
        }



@Override
public List<Societe> findAll() {
        return societeDAO.findAll();
        }



@Override
public void delete(Integer id) {
        societeDAO.deleteById(id);

        }
@Override
public List<Depot> getStocksBySocieteId(Integer id) {
        Optional<Societe> societe = societeDAO.findById(id);
        if (societe.isPresent()) {
                return societe.get().getStockList(); //getstockbyIdEntreprise
        } else {
                throw new MyResourceNotFoundException("Societe not found with id " + id);
        }
}

        @Override
        public List<Gestionnaire> getGestionnaire(Integer Id) {
        Societe societe = societeDAO.findById(Id).orElse(null);
        if(societe == null){
                return (List<Gestionnaire>)
                        ResponseEntity.notFound().build();
        }
        return societe.getGestList();
}

        @Override
        public List<Produit> getProduit(Integer Id) {
        Societe societe = societeDAO.findById(Id).orElse(null);
        if(societe == null){
                return (List<Produit>)
                        ResponseEntity.notFound().build();

        }
        return societe.getProdList();}
}
