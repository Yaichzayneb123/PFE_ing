package com.auth.security.DTO;

import com.auth.security.Entity.SousOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class VariantDTO {

    private Integer idproduit;
    private  List<SousOption> sousOptionList ;
    private List<Integer> sousOptionId;




}
