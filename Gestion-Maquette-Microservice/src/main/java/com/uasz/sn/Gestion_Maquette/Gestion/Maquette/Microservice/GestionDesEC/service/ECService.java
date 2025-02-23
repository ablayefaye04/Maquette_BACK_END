package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.service;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.model.EC;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.repository.ECRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ECService {
    @Autowired
    private ECRepository ecRepository;
    /*@Autowired
    private EnseignementService enseignementService;
*/
    public EC create(EC ec){
        return ecRepository.save(ec);
    }

    public EC update(EC ec){
        EC ecExisting = ecRepository.findById(ec.getId()).get();
        if(ecExisting == null){
            return null;
        }else{
            return ecRepository.save(ec);
        }
    }

    public void delete(EC ec){
        ecRepository.delete(ec);
    }

    public List<EC> findAll(){
        return ecRepository.findAll();
    }
    public EC findByIntitule(String intitule){
        return ecRepository.findByIntitule(intitule);
    }

    public  EC findById(Long id){
        return ecRepository.findById(id).get();
    }
    public EC findByCode(String code){
        return ecRepository.findByCode(code);
    }

    public void activer(Long id){
        EC ec = ecRepository.findById(id).get();
        if(ec!= null){
            if(ec.isActive()){
                ec.setActive(false);
            }else{
                ec.setActive(true);
            }
            ecRepository.save(ec);
        }
    }

    public List<EC> findByIds(Long[] ids) {
        return ecRepository.findAllById(Arrays.asList(ids));
    }

    public void archiver(Long id){
        EC ec = ecRepository.findById(id).get();
        if(ec != null){
            if(ec.isArchive()){
                ec.setArchive(false);
            }else{
                ec.setArchive(true);
            }
        }
    }
}
