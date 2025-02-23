package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.repository;


import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.model.Maquette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MaquetteRepository extends JpaRepository<Maquette,Long> {
    @Query("SELECT m FROM Maquette m WHERE m.nom = :nom AND m.semestre = :semestre")
    public Maquette findByNomAndSemestre(@Param("nom") String nom,@Param("semestre") int semestre);
}
