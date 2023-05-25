package com.auth.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "commandeclient")

public class CommandeClient {
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
    @JoinColumn(name = "idclient")
    private Client client;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cmd_prod",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private List<Produit> produitList ;


    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "idsociete")
    //@JsonIgnoreProperties("cmdClientList")
    //private Societe societe;





    //@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  //@JoinTable(name = "cmd_variant",
          //joinColumns = @JoinColumn(name = "commande_id"),
          //inverseJoinColumns = @JoinColumn(name = "variant_id"))
  //private List<Variant> variantList ;

  //id prod tejbed variant  listvariantid
  //cmd id variant et quantité bl key value
  //id client o aala kol variant anehu produit li teb3u o nkssoulou quantité zetha maa liste
  //pour chaque varant nejbdu id produit
  // cmd esm cmd
  //cmd prod o variant zuz
  //variant cmd many to many
  //





}
