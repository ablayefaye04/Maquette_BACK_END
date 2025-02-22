package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.service;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseService {
    @Autowired
    private ClasseRepository classeRepository;

    public Classe create(Classe classe){
        return classeRepository.save(classe);
    }


    public Classe update(Classe classe){
        return classeRepository.save(classe);
    }

    public void delete(Classe classe){
        if(classeRepository.findById(classe.getId()).get() != null){
            classeRepository.delete(classe);
        }
    }

    public List<Classe> findAll(){
        return classeRepository.findAll();
    }

    public Classe findById(Long id){
        return classeRepository.findById(id).get();
    }

    public void archiver(Long id){
        Classe classe = classeRepository.findById(id).get();
        if(classe != null){
            if(classe.isArchive()){
                classe.setArchive(false);
            }else{
                classe.setArchive(true);
            }
        }
    }
}
