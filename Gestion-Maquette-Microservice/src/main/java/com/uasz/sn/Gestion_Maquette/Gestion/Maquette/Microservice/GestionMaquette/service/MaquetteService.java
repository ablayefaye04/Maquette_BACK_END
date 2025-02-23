package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.service;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.service.UEService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.model.Maquette;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.repository.MaquetteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaquetteService {
    @Autowired
    private MaquetteRepository maquetteRepository;
    @Autowired
    private UEService ueService;

    public Maquette create(Maquette maquette){
        return maquetteRepository.save(maquette);
    }

    public Maquette update(Maquette maquette){
        Maquette existingMaquette = maquetteRepository.findById(maquette.getId()).get();
        if(existingMaquette == null){
            return null;
        }else{
            return maquetteRepository.save(maquette);
        }
    }

    public void delete(Maquette maquette){
        Maquette existingMaquette = maquetteRepository.findById(maquette.getId()).get();
        if(existingMaquette != null){
            maquetteRepository.delete(maquette);
        }
    }

    public List<Maquette> findAll(){
        return maquetteRepository.findAll();
    }

    public  Maquette findById(Long id){
        return maquetteRepository.findById(id).get();
    }

    public Maquette findByNom(String nom,int semestre){
        return maquetteRepository.findByNomAndSemestre(nom,semestre);
    }

    public void archiver(Long id){
        Maquette maquette = maquetteRepository.findById(id).get();
        if(maquette != null){
            if(maquette.isArchive()){
                maquette.setArchive(false);
            }else{
                maquette.setArchive(true);
            }
        }
    }

    public void activer(Long id){
        Maquette maquette = maquetteRepository.findById(id).get();
        if(maquette != null){
            if(maquette.isActive()){
                maquette.setActive(false);
            }else{
                maquette.setActive(true);
            }
            maquetteRepository.save(maquette);
        }
    }

}
