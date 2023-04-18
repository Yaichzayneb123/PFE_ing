package com.auth.security.Repository;

import com.auth.security.Entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantDAO extends JpaRepository<Variant, Integer> {

}
