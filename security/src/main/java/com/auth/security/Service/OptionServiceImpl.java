package com.auth.security.Service;

import com.auth.security.DTO.OptionDTO;
import com.auth.security.DTO.SocieteDTO;
import com.auth.security.DTO.SousOptionDTO;
import com.auth.security.Entity.Option;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.SousOption;
import com.auth.security.Entity.Variant;
import com.auth.security.Repository.CategorieDAO;
import com.auth.security.Repository.OptionDAO;
import com.auth.security.Repository.SousOptionDAO;
import io.jsonwebtoken.io.IOException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class OptionServiceImpl implements OptionService{

    @Autowired
    private OptionDAO optionDAO;

    @Autowired
    private SousOptionDAO ssDAO;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public OptionDTO save(OptionDTO option) throws IOException {
                 Option opt =  new Option();
                 opt.setName(option.getName());
                List<SousOption> listsps = new ArrayList<>();
                List<SousOptionDTO> listspsDTO = new ArrayList<>();
                Option savedOption =optionDAO.save(opt);
                option.getSousOptionsList().forEach(op->{
                    listsps.add(ssDAO.save(op.mapToEntity(savedOption)));
                });
                         listsps.forEach(op->{
                             listspsDTO.add(SousOptionDTO.mapToDto(op));
        });
             //opt.setSousOptionsList(listsps);
             //listsps.forEach(sops -> sops.setOption(savedOption));
             OptionDTO optionDTO = new OptionDTO();
             optionDTO.setName(savedOption.getName());
             optionDTO.setId(savedOption.getId());
             optionDTO.setSousOptionsList(listspsDTO);
            return optionDTO;
        }


    @Override
    public OptionDTO update(Integer id, OptionDTO option) throws IOException {
        Option opt = optionDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Option not found with id " + id));

        // Update the option name
        opt.setName(option.getName());
       // opt.setSousOptionsList(newSousOptions);

        // Save the updated option to the database
        Option savedOption = optionDAO.save(opt);
        // Update the sous-options list
        List<SousOption> newSousOptions = new ArrayList<>();
        option.getSousOptionsList().forEach(op -> {
            SousOption sousOption = ssDAO.save(op.mapToEntity(savedOption));
            newSousOptions.add(sousOption);
        });


        // Create and return the updated OptionDTO
        OptionDTO updatedOptionDTO = new OptionDTO();
        updatedOptionDTO.setName(savedOption.getName());

        // Convert the list of SousOption entities to a list of SousOptionDTO objects
        List<SousOptionDTO> sousOptionDTOList = new ArrayList<>();
        savedOption.getSousOptionsList().forEach(sousOption -> {
            SousOptionDTO sousOptionDTO = new SousOptionDTO();
            sousOptionDTO.setName(sousOption.getName());
            sousOptionDTOList.add(sousOptionDTO);
        });
        updatedOptionDTO.setSousOptionsList(sousOptionDTOList);
        return updatedOptionDTO;
    }


    @Override
    public OptionDTO findById(Integer id) {
        Option option = optionDAO.findById(id).get();
        OptionDTO optionDTO=modelMapper.map(option, OptionDTO.class);
        return  optionDTO;
    }

    @Override
    public void Delete(Integer id) {

    }
    @Override
    public List<OptionDTO> getAllOptionDTO() {
            List<Option> options = optionDAO.findAll();
            List<OptionDTO> optionDTOs = new ArrayList<>();
            for (Option option : options) {
                OptionDTO optionDTO = new OptionDTO();
                optionDTO.setId(option.getId());
                optionDTO.setName(option.getName());
                List<SousOptionDTO> sousOptionDTOs = new ArrayList<>();
                for (SousOption sousOption : option.getSousOptionsList()) {
                    SousOptionDTO sousOptionDTO = new SousOptionDTO();
                    sousOptionDTO.setId(sousOption.getId());
                    sousOptionDTO.setName(sousOption.getName());
                    sousOptionDTOs.add(sousOptionDTO);
                }
                optionDTO.setSousOptionsList(sousOptionDTOs);
                optionDTOs.add(optionDTO);
            }
            return optionDTOs;
        }
}
