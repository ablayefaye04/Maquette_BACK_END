package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.model;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.model.EC;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.model.Maquette;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"maquette_id", "ec_id"}))
public class Enseignement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ec_id", nullable = false)
    private EC ec;
    @ManyToOne
    @JoinColumn(name = "maquette_id", nullable = false)
    private Maquette maquette;
}