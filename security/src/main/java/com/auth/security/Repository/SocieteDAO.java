package com.auth.security.Repository;

import com.auth.security.DTO.SocieteDTO;
import com.auth.security.Entity.Societe;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SocieteDAO extends JpaRepository<Societe, Integer> {


@Override
    Optional<Societe> findById(Integer integer);

    @Override
    List<Societe> findAll();
}
