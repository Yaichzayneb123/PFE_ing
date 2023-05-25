package com.auth.security.Repository;

import com.auth.security.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDAO extends JpaRepository<Client,Integer> {
    List<Client> getClientsByIdEntreprise(Integer idEntreprise);
    //count colonne li chyehsebha

    @Query("SELECT COUNT(c) FROM Client c")
    int countAllClients();


}
