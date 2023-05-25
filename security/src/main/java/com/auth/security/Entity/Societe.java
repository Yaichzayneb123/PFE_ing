package com.auth.security.Entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "societe")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Societe {
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID uuid=UUID.randomUUID();
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "COMPANY_NAME")

        private String nomSociete;

        @Column(name = "Email")
        private String email;

        @Column(name = "Phone")
        private String tel;

        @Column(name = "Matricule")
        private String mat;

        @Column(name = "WEB_SITE")
        private String site;

        @Column(name = "Reg")
        private String reg;


        @Column(name = "LOGO")
        private String logo;

        @Column(name = "verified")
        private boolean verified = false;

        @Column(name = "password")
        private String password = UUID.randomUUID().toString();

        @OneToMany(mappedBy = "societe")
        @JsonManagedReference
        private List<Gestionnaire> gestList;

        @OneToMany(mappedBy = "societe")
        @JsonManagedReference
        private List<Produit> prodList;

        @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
        @Column(name = "stocks")
        @JsonManagedReference
        private List<Depot> stockList;

        //@OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
        //@Column(name = "cmdClients")
        //@JsonIgnoreProperties({"societe", "produitList", "stockList"})
        //private List<CommandeClient> cmdClientList;





}
