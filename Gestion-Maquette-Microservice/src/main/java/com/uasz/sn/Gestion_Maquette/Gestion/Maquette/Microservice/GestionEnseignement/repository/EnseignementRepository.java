package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.repository;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.model.Enseignement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface EnseignementRepository extends JpaRepository<Enseignement,Long> {
    public List<Enseignement> findByEcId(Long ecId);
    public List<Enseignement> findByMaquetteId(Long maquetteId);
    public Enseignement findByEcIdAndMaquetteId(Long ecId,Long maquetteId);
}
