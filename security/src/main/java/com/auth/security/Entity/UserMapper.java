package com.auth.security.Entity;

public class UserMapper {

    public static Users toUser(Gestionnaire gest){
        Users user = new Users();
        user.setTenantId(gest.getId());
        user.setEmail(gest.getEmail());

//        user.setImage(gest.getImage());
        user.setRole(Role.GestionnaireDeStock);
        return (user);
    }
    public static Users toUser(Societe soc){
        Users user = new Users();
        user.setEmail(soc.getEmail());
        user.setPassword(soc.getPassword());
        user.setRole(Role.ADMIN);
        user.setTenantId(soc.getId());
        return (user);
    }
}

