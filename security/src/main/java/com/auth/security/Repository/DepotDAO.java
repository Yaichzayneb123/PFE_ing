package com.auth.security.Repository;

import com.auth.security.Entity.Societe;
import com.auth.security.Entity.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepotDAO extends JpaRepository<Depot, Integer> {
    List<Depot> findBySociete(Optional<Societe> societe);
    List<Depot> findAll();
}
