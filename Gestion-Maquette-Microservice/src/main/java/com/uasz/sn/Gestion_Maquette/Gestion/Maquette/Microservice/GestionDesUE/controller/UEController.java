package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.controller;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.model.EC;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.service.ECService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.model.UE;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.service.UEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ues")
public class UEController {
    @Autowired
    private UEService ueService;
    @Autowired
    private ECService ecService;

    @GetMapping("")
    public ResponseEntity<List<UE>> afficherListe(){
        return new ResponseEntity<>(ueService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<UE> ajouterUE(@RequestBody UE ue) {
        UE nouvelleUE = ueService.create(ue);
        return ResponseEntity.status(201).body(nouvelleUE);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<UE> modifierUE(@PathVariable Long id, @RequestBody UE ue, @RequestParam(required = false) Long[] idECs) {
        UE ue1 = ueService.findById(id);
        if (ue1 == null) {
            return ResponseEntity.notFound().build();
        }

        if (idECs != null) {
            List<EC> ecs = ecService.findByIds(idECs);
            ue1.setEcList(ecs);
        }
        ue1.setCredit(ue.getCredit());
        ue1.setCoefficient(ue.getCoefficient());
        ue1.setIntitule(ue.getIntitule());
        ue1.setArchive(ue.isArchive());
        ue1.setActive(ue.isActive());

        UE updatedUE = ueService.update(ue1);
        return ResponseEntity.ok(updatedUE);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<Void> supprimerUE(@PathVariable Long id) {
        UE ue = ueService.findById(id);
        if (ue == null) {
            return ResponseEntity.notFound().build();
        }
        ueService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<UE> voirDetail(@PathVariable Long id) {
        UE ue = ueService.findById(id);
        if (ue == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ue);
    }

    @PostMapping("/{id}/archiver")
    public ResponseEntity<Void> archiver(@PathVariable Long id) {
        UE ue = ueService.findById(id);
        if (ue == null) {
            return ResponseEntity.notFound().build();
        }
        ueService.archiver(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activer")
    public ResponseEntity<Void> activer(@PathVariable Long id) {
        UE ue = ueService.findById(id);
        if (ue == null) {
            return ResponseEntity.notFound().build();
        }
        ueService.activer(id);
        return ResponseEntity.ok().build();
    }
}
