package com.auth.security.Service;

import com.auth.security.DTO.GestionnaireDTO;
import com.auth.security.Entity.*;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.GestionnaireDAO;
import com.auth.security.Repository.SocieteDAO;
import com.auth.security.Repository.UserDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.io.IOException;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class GestionnaireServiceImpl implements GestionnaireService{
    @Autowired
    private GestionnaireDAO gestionnaireDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private SocieteDAO societeDAO;


    @Override
    public Gestionnaire save (GestionnaireDTO gestDTO) throws IOException {
//        GestionnaireDTO gestDTO = objectMapper.readValue(chaine, GestionnaireDTO.class);
        //gestDTO.setImage(fileLoaderService.loadFile(photo));
        Gestionnaire gest = new Gestionnaire();
            gest  =     modelMapper.map(gestDTO,Gestionnaire.class);
//validation besh ki nekteb nafss mail ma tet3adesh
        if (gestionnaireDAO.existsByEmail(gest.getEmail())) {
            throw new IllegalArgumentException("Un gestionnaire avec cette adresse e-mail existe déjà !");
        }

        Optional<Societe> soc= societeDAO.findById(gestDTO.getSociete());
        gest.setSociete(soc.get());
        emailServiceImpl.sendEmail(gest.getEmail(),"Validation par Email",
                "Cher Gestionnaire,\n  Votre username : " +gest.getEmail()+"\n "
                        + "Mot de passe : "+gest.getPassword());
        gest.setEmailVerified(true);
        gestionnaireDAO.save(gest);
        Users Valid = UserMapper.toUser(gest);
        Valid.setPassword(passwordEncoder.encode(gest.getPassword()));
        //Valid.setEmailVerified(true);
        userDAO.save(Valid);
        return gest;

    }



    @Override
    //njibhom b societe
    public List<Gestionnaire> getAllgest() {
        return gestionnaireDAO.findAll();
    }

    @Override
    public GestionnaireDTO findById(Integer id) {
        Gestionnaire gest =gestionnaireDAO.findById(id).get();
      GestionnaireDTO dto = new GestionnaireDTO();
      dto.setFirstName(gest.getFirstName());
      dto.setLastName(gest.getLastName());
      dto.setEmail(gest.getEmail());
      dto.setCountry(gest.getCountry());
      dto.setAddress(gest.getAddress());
      dto.setPhone(gest.getPhone());
        dto.setSociete(gest.getSociete().getId());
        return dto;
    }

    @Override

    public Gestionnaire updategest(Integer id, GestionnaireDTO dto) {
        Gestionnaire currentGest = gestionnaireDAO.findById(id)
                .orElseThrow(() -> new MyResourceNotFoundException("gestionnaire id not found "+ id));
        currentGest.setFirstName(dto.getFirstName());
        currentGest.setLastName(dto.getLastName());
        currentGest.setAddress(dto.getAddress());
        currentGest.setCountry(dto.getCountry());
        currentGest.setEmail(dto.getEmail());
        currentGest.setPhone(dto.getPhone());

        return gestionnaireDAO.save(currentGest);
    }

    @Override
    public void Delete(Integer id) {
        gestionnaireDAO.deleteById(id);
    }

    @Override
    public int countGests() {
        return gestionnaireDAO.countAllGests();
    }
}
