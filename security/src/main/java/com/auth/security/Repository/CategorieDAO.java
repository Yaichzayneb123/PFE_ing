package com.auth.security.Repository;

import com.auth.security.Entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieDAO extends JpaRepository<Categorie, Integer> {
}
