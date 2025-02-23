package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.controller;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.model.Formation;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionFormation.service.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formations")
public class FormationController {
    @Autowired
    private FormationService formationService;

    @GetMapping("")
    public ResponseEntity<List<Formation>> voirFormations() {
        List<Formation> formations = formationService.findAll();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Formation> ajouterFormation(@RequestBody Formation formation) {
        Formation nouvelleFormation = formationService.create(formation);
        return new ResponseEntity<>(nouvelleFormation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/modifierFormation")
    public ResponseEntity<Formation> modifierFormation(@PathVariable Long id, @RequestBody Formation formation1) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (formation1.getIntitule() != null) {
            formation.setIntitule(formation1.getIntitule());
        }
        formation.setArchive(formation1.isArchive());
        Formation formationModifiee = formationService.update(formation);
        return new ResponseEntity<>(formationModifiee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/supprimerFormation")
    public ResponseEntity<List<Formation>> supprimerFormation(@PathVariable Long id) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        formationService.delete(formation);
        List<Formation> formations = formationService.findAll();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @PutMapping("/{id}/archiver")
    public ResponseEntity<Formation> archiver(@PathVariable Long id) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        formationService.archiver(id);
        Formation formationArchivee = formationService.findById(id);
        return new ResponseEntity<>(formationArchivee, HttpStatus.OK);
    }

    @GetMapping("/{id}/classes")
    public ResponseEntity<List<Classe>> listeClasse(@PathVariable Long id) {
        Formation formation = formationService.findById(id);
        if (formation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Classe> classes = formation.getClasses();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }
}