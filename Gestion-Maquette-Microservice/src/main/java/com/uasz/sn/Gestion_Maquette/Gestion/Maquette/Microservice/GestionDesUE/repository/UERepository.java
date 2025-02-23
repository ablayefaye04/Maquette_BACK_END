package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.repository;


import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.model.UE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UERepository extends JpaRepository<UE,Long> {
    public UE findByIntitule(String intitule);
    public UE findByCode(String code);
}
