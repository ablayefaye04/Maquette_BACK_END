package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.repository;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FormationRepository extends JpaRepository<Formation,Long> {
    public Formation findByIntitule(String intitule);
}
