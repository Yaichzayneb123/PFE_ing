package com.auth.security.Repository;

import com.auth.security.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitDAO extends JpaRepository<Produit, Integer> {

}
