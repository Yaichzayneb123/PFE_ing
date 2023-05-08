package com.auth.security.Service;

import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.ProduitDTO;
import com.auth.security.Entity.Categorie;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Societe;
import com.auth.security.Entity.Depot;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.SocieteDAO;
import com.auth.security.Repository.DepotDAO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Transactional
public class DepotServiceImpl implements DepotService {

    @Autowired

    private DepotDAO depotDAO;
    @Autowired
    private SocieteDAO societeDAO;
    private ModelMapper modelMapper = new ModelMapper();
    //private List<Stock> stocks = new ArrayList<>();



    @Override
    public DepotDTO save(DepotDTO dto) {
        //Stock stock= modelMapper.map(dto, Stock.class);
        Depot depot= DepotDTO.toEntity(dto);
       // Optional<Societe> soc= societeDAO.findById(users.getSociete().getId());
        Optional<Societe> soc= societeDAO.findById(dto.getIdEntreprise());
        depot.setSociete(soc.get());
        Depot savedDepot =  depotDAO.save(depot);
        DepotDTO depotDTO = new DepotDTO();
        depotDTO.setAdresse(savedDepot.getAdresse());
        depotDTO.setName(savedDepot.getName());
        depotDTO.setQuantity(savedDepot.getQuantity());
        depotDTO.setIdEntreprise(savedDepot.getSociete().getId());
        return depotDTO;

    }

    @Override
    public void Delete(Integer id) {
        depotDAO.deleteById(id);
    }

    @Override
    public List<Depot> GetAll( ) {
         return depotDAO.findAll();
    }
    @Override
    public List<ProduitDTO> getProduitByDepotId(Integer id) {
        Optional<Depot> depot = depotDAO.findById(id);
        if (depot.isPresent()) {
            List<ProduitDTO> produitDTOList = new ArrayList<>();
            for (Produit produit : depot.get().getProduitList()) {
                ProduitDTO produitDTO = new ProduitDTO();
                produitDTO.setName(produit.getName());
                produitDTO.setDepotId(produit.getDepot().getId());
                produitDTO.setQuantity(produit.getQuantity());
                produitDTO.setImage(produit.getImage());
                produitDTO.setId(produit.getId());
                produitDTO.setInventoryStatus(produit.getInventoryStatus());
                produitDTO.setCategory(produit.getCategorie().getId());
                produitDTO.setDescription(produit.getDescription());
                produitDTO.setPrice(produit.getPrice());
                produitDTOList.add(produitDTO);
            }
            return produitDTOList;
        } else {
            throw new MyResourceNotFoundException("depot not found with id " + id);
        }
    }
    @Override
    public DepotDTO updateDepot (Integer id, DepotDTO depot) {
        Depot currentDepot = depotDAO.findById(id)
                .orElseThrow(() -> new MyResourceNotFoundException ("depot id not found "+ id));

        Optional<Societe> societe = societeDAO.findById(depot.getIdEntreprise());
        if (societe.isPresent()) {
            currentDepot.setSociete(societe.get());
        } else {
            throw new MyResourceNotFoundException("societe not found with id " + id);
        }
        currentDepot.setName(depot.getName());
        currentDepot.setAdresse(depot.getAdresse());
        currentDepot.setQuantity(depot.getQuantity());
        depotDAO.save(currentDepot);

        DepotDTO updatedDepotDTO = new DepotDTO();
        updatedDepotDTO.setId(currentDepot.getId());
        updatedDepotDTO.setName(currentDepot.getName());
        updatedDepotDTO.setAdresse(currentDepot.getAdresse());
        updatedDepotDTO.setQuantity(currentDepot.getQuantity());
        updatedDepotDTO.setIdEntreprise(currentDepot.getId());

        return updatedDepotDTO;


    }
}
