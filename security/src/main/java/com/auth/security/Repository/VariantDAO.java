package com.auth.security.Repository;

import com.auth.security.Entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantDAO extends JpaRepository<Variant, Integer> {
    @Query("SELECT SUM(v.quantity) FROM Variant v WHERE v.produit.Id = :produitId")
    Integer sumQuantitiesByProduitId(@Param("produitId") Integer produitId);



}
