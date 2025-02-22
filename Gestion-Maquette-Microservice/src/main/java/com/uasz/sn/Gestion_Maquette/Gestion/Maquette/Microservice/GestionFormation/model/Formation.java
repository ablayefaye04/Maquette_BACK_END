package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.model;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String intitule;
    private boolean archive ;
    @OneToMany(mappedBy = "formation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Classe> classes;
}
