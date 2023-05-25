package com.auth.security.Service;

import com.auth.security.DTO.DepotDTO;
import com.auth.security.DTO.OptionDTO;
import com.auth.security.DTO.SousOptionDTO;
import com.auth.security.DTO.VariantDTO;
import com.auth.security.Entity.*;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.ProduitDAO;
import com.auth.security.Repository.SousOptionDAO;
import com.auth.security.Repository.VariantDAO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class VariantServiceImpl implements VariantService{
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ProduitDAO produitDAO;
    @Autowired
    private VariantDAO variantDAO;

    @Autowired
    private SousOptionDAO sousOptionDAO;


    @Override
    public VariantDTO save(VariantDTO dto) throws Exception {
        List<SousOption> sousOptions = new ArrayList<>();
        VariantDTO variantDTO = new VariantDTO();

        // Créer l'objet Variant et initialiser sa quantité à celle du DTO
        Variant variant = Variant.builder()
                .quantity(dto.getQuantity())
                .build();

        // Ajouter les sous-options sélectionnées au variant
        List<SousOption> list = sousOptionDAO.findAllById(dto.getSousOptionId());
        sousOptions.addAll(list);
        variant.setSousOptionList(sousOptions);

        // Récupérer le produit correspondant
        Optional<Produit> optionalProduit = produitDAO.findById(dto.getIdproduit());
        if (!optionalProduit.isPresent()) {
            throw new Exception("Le produit n'existe pas");
        }
        Produit produit = optionalProduit.get();

        // Vérifier si la quantité du variant ne dépasse pas celle du produit
        Integer sumQuantities = variantDAO.sumQuantitiesByProduitId(produit.getId());
        if(sumQuantities == null){
            sumQuantities = 0;
        }
        int produitQuantity = produit.getQuantity();
        if (sumQuantities + variant.getQuantity() > produitQuantity)  {
            throw new Exception("La quantité de variant dépasse celle de produit");
        }

        // Sauvegarder le variant
        variant.setProduit(produit);
        Variant savedVariant = variantDAO.save(variant);

        // Mettre à jour le DTO
        variantDTO.setSousOptionList(savedVariant.getSousOptionList());
        variantDTO.setIdproduit(savedVariant.getProduit().getId());
        List<Integer> listIds = dto.getSousOptionId();
        variantDTO.setSousOptionId(listIds);
        variantDTO.setQuantity(savedVariant.getQuantity());

        return variantDTO;
    }






    @Override
    public List<Variant> getAllVariants() {
        return variantDAO.findAll();
    }

    @Override
    public void Delete(Integer id) {
        variantDAO.deleteById(id);
    }

    @Override
    public List<VariantDTO> getVariantsByIdProduit(Integer id) {
        Optional<Produit> produit = produitDAO.findById(id);
        if (produit.isPresent()) {
            List<VariantDTO> variantDTOList = new ArrayList<>();
            for (Variant variant : produit.get().getVariant()) {
                VariantDTO variantDTO = new VariantDTO();
                variantDTO.setIdproduit(variant.getProduit().getId());
                variantDTO.setSousOptionList(variant.getSousOptionList());
                List<Integer> sousOptionIds = variant.getSousOptionList().stream().map(SousOption::getId).collect(Collectors.toList());
                variantDTO.setSousOptionId(sousOptionIds);
                variantDTO.setQuantity(variant.getQuantity());
                variantDTOList.add(variantDTO);
            }
            return variantDTOList;
        } else {
            throw new MyResourceNotFoundException("Produit not found with id " + id);
        }
    }

    @Override
    public Variant updatevariant(Integer id, VariantDTO variant) {
            Variant var = variantDAO.findById(id)
                    .orElseThrow(() -> new MyResourceNotFoundException("variant id not found "+ id));

            //var.setColor(variant.getColor());
            return variantDAO.save(var);

    }
}
