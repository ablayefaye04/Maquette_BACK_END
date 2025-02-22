package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.controller;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.model.Formation;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formations")
public class FormationController {
    @Autowired
    private FormationService formationService;

    @GetMapping("")
    public List<Formation> voirFormations(){
        return formationService.findAll();
    }

    @PostMapping("/ajouter")
    public Formation ajouterFormation(@RequestBody Formation formation){
        return formationService.create(formation);
    }

    @PutMapping("/{id}/modifierFormation")
    public Formation modifierFormation(@PathVariable Long id,String intitule){
        Formation formation = formationService.findById(id);
        formation.setIntitule(intitule);
        return formationService.update(formation);
    }

    @DeleteMapping("/{id}/supprimerFormation")
    public List<Formation> supprimerFormation(@PathVariable Long id){
        Formation formation = formationService.findById(id);
        if(formation != null){
            formationService.delete(formation);
        }
        return formationService.findAll();
    }



    @PutMapping("/{id}/archiver")
    public Formation archiver(@PathVariable Long id){
        Formation formation = formationService.findById(id);
        if(formation != null){
            formationService.archiver(id);
        }
        return formationService.findAll()
                .stream()
                .filter(formation1 -> formation1.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Formation inexistante"));
    }

    @GetMapping("/{id}/classes")
    public List<Classe> listeClasse(@PathVariable Long id){
        return formationService.findById(id).getClasses();
    }
}
