package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.repository;


import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.model.EC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ECRepository extends JpaRepository<EC,Long> {
    public List<EC> findByUeId(Long id);
    public EC findByIntitule(String intitule);
    public EC findByCode(String code);
}

