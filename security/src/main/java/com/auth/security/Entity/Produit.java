package com.auth.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "produit")

public class Produit  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private String image;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "quantitycmd")
    private Integer quantityCmd;


    @Column(name = "inventoryStatus")
    private String inventoryStatus;

    @ManyToOne()
    @JoinColumn(name = "depot_id")
    @JsonBackReference
    private Depot depot;

    @OneToMany(mappedBy = "produit")
    @JsonManagedReference
    private List<Variant> variant;

    @ManyToOne
    @JoinColumn(name = "id_category")
    @JsonBackReference
    private Categorie categorie ;


    @ManyToOne
    @JoinColumn(name = "id_societe")
    @JsonBackReference
    private Societe societe;



}
