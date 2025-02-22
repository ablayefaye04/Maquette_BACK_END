package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.repository;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClasseRepository extends JpaRepository<Classe,Long> {
}
