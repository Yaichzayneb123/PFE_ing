package com.auth.security.Service;

import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.ProduitDTO;
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

import java.util.ArrayList;
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
        public List<DepotDTO> getDepotBySocieteId(Integer id) {
                Optional<Societe> societe = societeDAO.findById(id);
                if (societe.isPresent()) {
                        List<DepotDTO> depotDTOList = new ArrayList<>();
                        for (Depot depot : societe.get().getStockList()) {
                                DepotDTO depotDTO = new DepotDTO();
                                depotDTO.setName(depot.getName());
                                depotDTO.setAdresse(depot.getAdresse());
                                depotDTO.setQuantity(depot.getQuantity());
                                depotDTO.setIdEntreprise(depot.getSociete().getId());
                                depotDTO.setId(depot.getId());
                                depotDTOList.add(depotDTO);
                        }
                        return depotDTOList;
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
        public List<ProduitDTO> getProduit(Integer Id) {
        Societe societe = societeDAO.findById(Id).orElse(null);
        if(societe == null){
                return (List<ProduitDTO>)
                        ResponseEntity.notFound().build();

        }
                List<Produit> produits = societe.getProdList();
                List<ProduitDTO> customProduits = new ArrayList<>();
                for (Produit produit : produits) {
                        ProduitDTO dto = new ProduitDTO();
                        dto.setId(produit.getId());
                        dto.setCategory(produit.getCategorie().getId());
                        dto.setName(produit.getName());
                        dto.setDescription(produit.getDescription());
                        dto.setPrice(produit.getPrice());
                        dto.setImage(produit.getImage());
                        dto.setQuantity(produit.getQuantity());
                        dto.setInventoryStatus(produit.getInventoryStatus());
                        customProduits.add(dto);
                }
                return customProduits;
}
}
