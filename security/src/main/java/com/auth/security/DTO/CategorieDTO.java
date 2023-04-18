package com.auth.security.DTO;

import com.auth.security.Entity.Variant;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
public class CategorieDTO {

    private String name;
    private String designation;
   // private List<Variant> variantList;
}
