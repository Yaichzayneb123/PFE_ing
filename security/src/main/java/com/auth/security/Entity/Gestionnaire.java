package com.auth.security.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "GestionnaireDeStock")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Gestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "email" ,unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password = UUID.randomUUID().toString();

    @Column(name = "country")
    private String country;

//    @Column(name = "image")
//    private String image;

//    private String EmailVerificationKey ;
    private Boolean EmailVerified=false;

    @ManyToOne
    @JoinColumn(name = "id_societe")
    @JsonBackReference
    private Societe societe;
}
