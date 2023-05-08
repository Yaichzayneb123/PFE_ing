package com.auth.security.DTO;

import com.auth.security.Entity.Variant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CategorieDTO {

    private String name;
    private String designation;
    private String description;
   // private List<Variant> variantList;
}
