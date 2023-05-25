package com.auth.security.Entity;

public class UserMapper {

    public static Users toUser(Gestionnaire gest){
        Users user = new Users();
        user.setEmail(gest.getEmail());

//        user.setImage(gest.getImage());
        user.setRole(Role.GestionnaireDeStock);
        user.setTenantId(gest.getId());
        return (user);
    }
    public static Users toUser(Societe soc){
        Users user = new Users();
        user.setEmail(soc.getEmail());
        user.setPassword(soc.getPassword());
        user.setRole(Role.ADMIN);
        //user.setImage(soc.getLogo());
        user.setTenantId(soc.getId());
        return (user);
    }
}

