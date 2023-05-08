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
    public VariantDTO save (VariantDTO dto) {
        List<SousOption> sousOptions = new ArrayList<>();
        VariantDTO variantDTO = new VariantDTO();
        var variant=Variant.builder()
                .build();
        List<SousOption> list= sousOptionDAO.findAllById(dto.getSousOptionId());
        sousOptions.addAll(list);
        variant.setSousOptionList(sousOptions);
        Optional<Produit> produit = produitDAO.findById(dto.getIdproduit());
        variant.setProduit(produit.get());

       Variant saved= variantDAO.save(variant);
        variantDTO.setSousOptionList(saved.getSousOptionList());
        variantDTO.setIdproduit(saved.getProduit().getId());
        List<Integer>listIds=dto.getSousOptionId();
        variantDTO.setSousOptionId(listIds);
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
