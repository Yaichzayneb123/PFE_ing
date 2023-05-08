package com.auth.security.Service;

import com.auth.security.DTO.ProduitDTO;
import com.auth.security.DTO.VariantDTO;
import com.auth.security.Entity.Produit;
import com.auth.security.Entity.Variant;

import java.util.List;

public interface VariantService {
    VariantDTO save (VariantDTO dto) ;
    List<Variant> getAllVariants();
    void Delete (Integer id);
    Variant updatevariant(Integer id, VariantDTO variant);
    List<VariantDTO> getVariantsByIdProduit (Integer id);
}

