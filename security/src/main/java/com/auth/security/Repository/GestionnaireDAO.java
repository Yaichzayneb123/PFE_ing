package com.auth.security.Repository;

import com.auth.security.Entity.CommandeClient;
import com.auth.security.Entity.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GestionnaireDAO extends JpaRepository<Gestionnaire,Integer> {




    @Query("SELECT COUNT(c) FROM Gestionnaire c")
    int countAllGests();

    boolean existsByEmail(String email);

}
