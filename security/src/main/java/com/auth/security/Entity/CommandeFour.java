package com.auth.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commandefour")
public class CommandeFour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "code")
    private String code = UUID.randomUUID().toString().substring(0, 8);

    @Column(name = "datecommande")
    private LocalDateTime dateCommande;

    @Column(name = "montant")
    private Integer montant;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idFour")
    private Fournisseur fournisseur;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cmdFour_prod",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private List<Produit> produitList ;

}
