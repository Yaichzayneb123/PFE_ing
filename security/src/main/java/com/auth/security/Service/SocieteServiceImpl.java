package com.auth.security.Service;

import com.auth.security.DTO.*;
import com.auth.security.Entity.*;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.CmdClientDAO;
import com.auth.security.Repository.CmdFourDAO;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@AllArgsConstructor
@Transactional
@Service
public class SocieteServiceImpl implements SocieteService {

    @Autowired
    private SocieteDAO societeDAO;
    @Autowired
    private CmdClientDAO cmdClientDAO;
    @Autowired
    private CmdFourDAO cmdFourDAO;
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EmailServiceImpl emailServiceImpl;


    @Override
    public Societe save(SocieteDTO dto) {
        Societe societe = modelMapper.map(dto, Societe.class);
        societeDAO.save(societe);
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
    public List<SocieteDTO> getAll() {
        List<Societe> societes = societeDAO.findAll();
        List<SocieteDTO> societeDTOList = new ArrayList<>();
        for(Societe societe: societes){
            SocieteDTO dto = new SocieteDTO();
            dto.setEmail(societe.getEmail());
            dto.setNomSociete(societe.getNomSociete());
            dto.setReg(societe.getReg());
            dto.setMat(societe.getMat());
            dto.setVerified(societe.isVerified());
            dto.setSite(societe.getSite());
            dto.setTel(societe.getTel());
            dto.setId(societe.getId());
            societeDTOList.add(dto) ;

        }
        return societeDTOList;
    }


    @Override
    public void delete(Integer id) {
        societeDAO.deleteById(id);

    }
    @Override
    public  SocieteDTO validateSociete(Integer id){
        Optional<Societe> soc= societeDAO.findById(id);
        emailServiceImpl.sendEmail(soc.get().getEmail(),"Validation par Email",
                "Cher Admin,\n  Votre username : " +soc.get().getEmail()+"\n "
                        + "Mot de passe : "+soc.get().getPassword());
        soc.get().setVerified(true);
        Societe societe=societeDAO.save(soc.get());
        Users Valid = UserMapper.toUser(soc.get());
        Valid.setPassword(passwordEncoder.encode(soc.get().getPassword()));

        userDAO.save(Valid);
        return modelMapper.map(societe, SocieteDTO.class);
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
    public List<CmdClientDTO> getCommandeByIdSociete(Integer id) {
        List<CommandeClient> list = cmdClientDAO.findCmdBySociete(id);

        List<CmdClientDTO> commandeDTOList = new ArrayList<>();
        for (CommandeClient commande : list) {
            CmdClientDTO commandeDTO = new CmdClientDTO();
            commandeDTO.setId(commande.getId());
            commandeDTO.setDateCommande(commande.getDateCommande());
            commandeDTO.setCode(commande.getCode());
            commandeDTO.setMontant(commande.getMontant());
            commandeDTO.setClient(commande.getClient().getFirstName()+" "+commande.getClient().getLastName());
            commandeDTO.setIdClient(commande.getClient().getId());
            commandeDTO.setIdEntreprise(commande.getIdEntreprise());
            List<ProduitDTO> dtolist = new ArrayList<>();
            for (Produit produit : commande.getProduitList()) {
                ProduitDTO prod = ProduitDTO.fromEntity(produit);
                dtolist.add(prod);
            }
            commandeDTO.setProduitList(dtolist);
            commandeDTOList.add(commandeDTO);

            List<Integer> produitIds = commande.getProduitList().stream().map(Produit::getId).collect(Collectors.toList());
            commandeDTO.setListProdId(produitIds);


        }
        return commandeDTOList;

    }


    @Override
    public List<CmdFourDTO> getCommandeFourByIdSociete(Integer id) {
        List<CommandeFour> list = cmdFourDAO.findCmdBySocietee(id);

        List<CmdFourDTO> commandeFourList = new ArrayList<>();
        for (CommandeFour commande : list) {
            CmdFourDTO commandeDTO = new CmdFourDTO();
            commandeDTO.setId(commande.getId());
            commandeDTO.setDateCommande(commande.getDateCommande());
            commandeDTO.setCode(commande.getCode());
            commandeDTO.setMontant(commande.getMontant());
            commandeDTO.setFournisseur(commande.getFournisseur().getFirstName() + " " + commande.getFournisseur().getLastName());
            commandeDTO.setIdFour(commande.getFournisseur().getId());
            commandeDTO.setIdEntreprise(commande.getIdEntreprise());
            List<ProduitDTO> dtolist = new ArrayList<>();
            for (Produit produit : commande.getProduitList()) {
                ProduitDTO prod = ProduitDTO.fromEntity(produit);
                dtolist.add(prod);
            }
            commandeDTO.setProduitList(dtolist);
            commandeFourList.add(commandeDTO);

            List<Integer> produitIds = commande.getProduitList().stream().map(Produit::getId).collect(Collectors.toList());
            commandeDTO.setListProdId(produitIds);

        }
            return commandeFourList;
    }

    @Override
    public List<Gestionnaire> getGestionnaire(Integer Id) {
        Societe societe = societeDAO.findById(Id).orElse(null);
        if (societe == null) {
            return (List<Gestionnaire>)
                    ResponseEntity.notFound().build();
        }
        return societe.getGestList();
    }

    @Override
    public List<ProduitDTO> getProduit(Integer Id) {
        Societe societe = societeDAO.findById(Id).orElse(null);
        if (societe == null) {
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
