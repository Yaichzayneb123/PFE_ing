package com.auth.security.Service;

import com.auth.security.DTO.DepotDTO;
import com.auth.security.Entity.Societe;
import com.auth.security.Entity.Depot;
import com.auth.security.Repository.SocieteDAO;
import com.auth.security.Repository.DepotDAO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Depot save(DepotDTO dto) {
        //Stock stock= modelMapper.map(dto, Stock.class);
        Depot stock= DepotDTO.toEntity(dto);
       // Optional<Societe> soc= societeDAO.findById(users.getSociete().getId());
        Optional<Societe> soc= societeDAO.findById(dto.getIdEntreprise());
        stock.setSociete(soc.get());
        return depotDAO.save(stock);
       // return stock;
    }

    @Override
    public List<Depot> GetAll( ) {
         return depotDAO.findAll();
    }
}
