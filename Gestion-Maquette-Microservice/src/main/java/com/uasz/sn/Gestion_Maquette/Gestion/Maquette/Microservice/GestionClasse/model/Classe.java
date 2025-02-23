package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.model.Formation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "classe",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"formation_id", "niveau"})
        }
)
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private int niveau;
    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;

    private boolean  archive;
}
