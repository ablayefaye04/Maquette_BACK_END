package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.service;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.model.Enseignement;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.repository.EnseignementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignementService {
    @Autowired
    private EnseignementRepository enseignementRepository;

    public Enseignement create(Enseignement enseignement){
        return enseignementRepository.save(enseignement);
    }

    public boolean Exist(Long ec, Long maquette){
        if(enseignementRepository.findByEcIdAndMaquetteId(ec,maquette) != null){
            return true;
        }else {
            return false;
        }
    }
    public Enseignement update(Enseignement enseignement){
        Enseignement existing = enseignementRepository.findById(enseignement.getId()).get();
       return (enseignement == null ? null : enseignementRepository.save(enseignement));
    }

    public void delete(Enseignement enseignement){
        enseignementRepository.delete(enseignement);
    }

    public List<Enseignement> findAll(){
        return enseignementRepository.findAll();
    }

    public   Enseignement findById(Long id){
        return enseignementRepository.findById(id).get();
    }

    public Enseignement findByEcAndMaquette(Long ec,Long maquette){
        return enseignementRepository.findByEcIdAndMaquetteId(ec,maquette);
    }

    public List<Enseignement> findByMaquette(Long maquette){
        return enseignementRepository.findByMaquetteId(maquette);
    }

    public List<Enseignement> findByEc(Long ec){
        return enseignementRepository.findByEcId(ec);
    }
}
