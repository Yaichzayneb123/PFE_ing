package com.auth.security.Service;

import com.auth.security.DTO.VariantDTO;
import com.auth.security.Entity.*;
import com.auth.security.Exception.MyResourceNotFoundException;
import com.auth.security.Repository.ProduitDAO;
import com.auth.security.Repository.VariantDAO;
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
public class VariantServiceImpl implements VariantService{
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private ProduitDAO produitDAO;
    @Autowired
    private VariantDAO variantDAO;

    @Override
    public VariantDTO save(VariantDTO dto) {
        Variant variant = modelMapper.map(dto , Variant.class);
        Optional<Produit> prod = produitDAO.findById(dto.getIdproduit());
        variant.setProduit(prod.get());
        variantDAO.save(variant);
        return dto;
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
    public Variant updatevariant(Integer id, VariantDTO variant) {
            Variant var = variantDAO.findById(id)
                    .orElseThrow(() -> new MyResourceNotFoundException("variant id not found "+ id));

            var.setColor(variant.getColor());
            var.setTaille(variant.getTaille());
            return variantDAO.save(var);

    }
}
