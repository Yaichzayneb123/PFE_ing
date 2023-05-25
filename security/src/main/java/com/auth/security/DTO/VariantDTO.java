package com.auth.security.DTO;

import com.auth.security.Entity.Produit;
import com.auth.security.Entity.SousOption;
import com.auth.security.Entity.Variant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class VariantDTO {

    private Integer idproduit;
    private  List<SousOption> sousOptionList ;
    private List<Integer> sousOptionId;
    private Integer quantity;

    public static VariantDTO fromEntity(Variant variant) {
        VariantDTO dto = new VariantDTO();
        dto.setIdproduit(variant.getProduit().getId());
        dto.setSousOptionList(variant.getSousOptionList());
        dto.setSousOptionId(variant.getSousOptionList().stream().map(SousOption::getId).collect(Collectors.toList()));
        dto.setQuantity(variant.getQuantity());
        return dto;
    }

    public static Variant toEntity(VariantDTO dto) {
        if (dto == null) {
            return null;
        }
        Variant entity = new Variant();

        List<SousOption> sousOptionList = new ArrayList<>();
        if (dto.getSousOptionId() != null) {
            for (Integer id : dto.getSousOptionId()) {
                SousOption sousOption = new SousOption();
                sousOption.setId(id);
                sousOptionList.add(sousOption);
            }
        }
        entity.setSousOptionList(sousOptionList);

        return entity;
    }


}
