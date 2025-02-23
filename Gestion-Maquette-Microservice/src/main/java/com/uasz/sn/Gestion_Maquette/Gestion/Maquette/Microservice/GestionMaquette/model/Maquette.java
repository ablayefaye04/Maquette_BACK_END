package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.model;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.model.UE;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.model.Enseignement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "maquette",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"classe_id", "semestre"})}
)
public class Maquette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nom;
    private int semestre;
    private boolean archive;
    private boolean active;


    @ManyToMany
    @JoinTable(
            name = "maquette_ue",
            joinColumns = @JoinColumn(name = "maquette_id"),
            inverseJoinColumns = @JoinColumn(name = "ue_id")
    )
    private List<UE> ueList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @OneToMany(mappedBy = "maquette", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enseignement> enseignements = new ArrayList<>();

}
