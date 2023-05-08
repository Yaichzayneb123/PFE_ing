package com.auth.security.Entity;

import com.auth.security.DTO.SousOptionDTO;
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
@Table(name= "optionn")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name")
    private String name;

    //@OneToMany(mappedBy = "option")
    //@JsonManagedReference
    //private List<SousOption> sousOptionsList ;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SousOption> sousOptionsList;
}
