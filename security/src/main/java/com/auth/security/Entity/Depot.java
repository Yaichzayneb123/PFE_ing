package com.auth.security.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "depot")

public class Depot {
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid=UUID.randomUUID();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codeproduit")
    private String codeProduit;


    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsociete")
    @JsonBackReference
    private Societe societe;


    @OneToMany(mappedBy = "depot", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Produit> produit;






}

