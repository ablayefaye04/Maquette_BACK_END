package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.controller;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.service.ClasseService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.model.Formation;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasseController {
    @Autowired
    private ClasseService classeService;
    @Autowired
    private FormationService formationService;

    @GetMapping("")
    public List<Classe> liste(){
        return  classeService.findAll();
    }

    @PostMapping("/{idFormation}/ajouter")
    public Classe ajouter(@PathVariable Long idFormation,@RequestBody Classe classe){
        Formation formation = formationService.findById(idFormation);
        classe.setFormation(formation);
        Classe classe1 = classeService.create(classe);
        return (classe == null ? null : classe);
    }

    @PutMapping("/{id}/modifier")
    public Classe modifier(@PathVariable Long id, Classe classe){
        Classe classe1 = classeService.findById(id);
        if(!classe1.equals(classe) && classe != null){
            if(classe.getFormation() != null){
                classe1.setFormation(classe.getFormation());
            }
            if(classe.isArchive()){
                classe1.setArchive(true);
            }else{
                classe1.setArchive(false);
            }
        }
        return classeService.update(classe1);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Classe classe = classeService.findById(id);
        classeService.delete(classe);
    }
}
