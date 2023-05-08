package com.auth.security.Repository;

import com.auth.security.Entity.SousOption;
import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousOptionDAO extends JpaRepository<SousOption,Integer> {
}
