package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.controller;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.service.ClasseService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.model.Formation;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.service.FormationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasseController {
    private final ClasseService classeService;
    private final FormationService formationService;

    public ClasseController(ClasseService classeService, FormationService formationService) {
        this.classeService = classeService;
        this.formationService = formationService;
    }

    @GetMapping("")
    public ResponseEntity<List<Classe>> liste() {
        return ResponseEntity.ok(classeService.findAll());
    }

    @PostMapping("/{idFormation}/ajouter")
    public ResponseEntity<Classe> ajouter(@PathVariable Long idFormation, @RequestBody Classe classe) {
        Formation formation = formationService.findById(idFormation);
        if (formation != null) {
            classe.setFormation(formation);
            Classe nouvelleClasse = classeService.create(classe);
            return ResponseEntity.status(201).body(nouvelleClasse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<Classe> modifier(@PathVariable Long id, @RequestBody Classe classeDetails) {
        Classe classe = classeService.findById(id);
        if (classe != null) {
            if (classeDetails.getFormation() != null) {
                classe.setFormation(classeDetails.getFormation());
            }
            classe.setNiveau(classeDetails.getNiveau());
            if(classe.isArchive() != classeDetails.isArchive()){
                classe.setArchive(classeDetails.isArchive());
            }
            Classe updatedClasse = classeService.update(classe);
            return ResponseEntity.ok(updatedClasse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Classe classe = classeService.findById(id);
        if (classe != null) {
            classeService.delete(classe);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
