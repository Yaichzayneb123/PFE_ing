package com.auth.security.DTO;

import com.auth.security.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocieteDTO {
    private Integer id;
    private String nomSociete;
    private String email;
    private String tel;
    private String mat;
    private String site;
    private String reg;
    private String logo;
    private String password;
    private boolean verified = false;




}
