package com.auth.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "variant")
public class Variant  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "taille")
    private String Taille;

    @Column(name = "color")
    private String color;

    @ManyToOne()
    @JoinColumn(name = "idproduit")
    @JsonBackReference
    private Produit produit;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcategorie")
    @JsonBackReference
    private Categorie categorie;


}
