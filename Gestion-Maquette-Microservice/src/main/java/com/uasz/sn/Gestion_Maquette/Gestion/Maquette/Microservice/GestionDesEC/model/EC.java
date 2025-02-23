package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.model;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.model.UE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(unique = true, nullable = false)
    private String intitule;
    private int CM;
    private int TD;
    private int TP;
    private int coefficient;

    @ManyToOne
    private UE ue;

    private boolean active;
    private boolean archive;

   /* // L'association avec Enseignement est uniquement un @OneToMany
    @OneToMany(mappedBy = "ec", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Enseignement> enseignements;
*/
    // Le constructeur, getters et setters sont générés par Lombok
}
