package com.auth.security.Service;

import com.auth.security.DTO.ClientDTO;
import com.auth.security.DTO.FournisseurDTO;
import com.auth.security.Entity.Client;
import com.auth.security.Entity.Fournisseur;
import com.auth.security.Repository.FournisseurDAO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {
    @Autowired
    private FournisseurDAO fournisseurDAO;

    @Override
    public FournisseurDTO save(FournisseurDTO fournisseurDTO) {
        // Convertir l'objet DTO en entité
        Fournisseur fournisseur = FournisseurDTO.toEntity(fournisseurDTO);

        // Enregistrer l'entité en base de données
        fournisseur = fournisseurDAO.save(fournisseur);

        // Mettre à jour l'ID de l'objet DTO
        fournisseurDTO.setId(fournisseur.getId());

        // Retourner l'objet DTO mis à jour
        return fournisseurDTO;
    }

    @Override
    public List<FournisseurDTO> getFoursByIdEntreprise(Integer idEntreprise) {
        List<FournisseurDTO> fournisseurList  = new ArrayList<>();
        List<Fournisseur> fourn = fournisseurDAO.getFourByIdEntreprise(idEntreprise);

        for (Fournisseur fournisseur : fourn){
            FournisseurDTO fournisseurDTO = FournisseurDTO.fromEntity(fournisseur);
            fournisseurList.add(fournisseurDTO);

        }
        return fournisseurList ;
    }


    @Override
    public void Delete(Integer id) {
        fournisseurDAO.deleteById(id);

    }

    @Override
    public FournisseurDTO update(Integer id, FournisseurDTO dto) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid client ID");
        }
        Fournisseur fournisseur = fournisseurDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        fournisseur.setFirstName(dto.getFirstName());
        fournisseur.setLastName(dto.getLastName());
        fournisseur.setEmail(dto.getEmail());
        fournisseur.setAddress(dto.getAddress());
        fournisseur.setPhone(dto.getPhone());
        fournisseur.setIdEntreprise(dto.getIdEntreprise());
        fournisseurDAO.save(fournisseur);

        return FournisseurDTO.fromEntity(fournisseur);
    }

    @Override
    public int countFourns() {
        return 0;
    }
}
