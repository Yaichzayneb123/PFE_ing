package com.auth.security.Repository;

import com.auth.security.DTO.OptionDTO;
import com.auth.security.Entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OptionDAO extends JpaRepository<Option, Integer> {
    Optional<Option> findById(Integer id);
}
