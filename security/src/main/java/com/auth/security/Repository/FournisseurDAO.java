package com.auth.security.Repository;

import com.auth.security.Entity.Client;
import com.auth.security.Entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FournisseurDAO extends JpaRepository<Fournisseur,Integer> {
    List<Fournisseur> getFourByIdEntreprise(Integer idEntreprise);
}
