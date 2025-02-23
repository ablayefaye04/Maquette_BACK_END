package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.controller;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.model.EC;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.service.ECService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.model.UE;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.service.UEService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.model.Enseignement;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.service.EnseignementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecs")
public class ECController {

    @Autowired
    private ECService ecService;
    @Autowired
    private UEService ueService;
    @Autowired
    private EnseignementService enseignementService;

    @GetMapping("")
    public ResponseEntity<List<EC>> voirListe() {
        List<EC> ecs = ecService.findAll();
        return ResponseEntity.ok(ecs);
    }

    @PostMapping("/{idUE}/ajouter")
    public ResponseEntity<EC> ajouterEC(@PathVariable Long idUE, @RequestBody EC ec) {
        UE ue = ueService.findById(idUE);
        if (ue == null) {
            return ResponseEntity.notFound().build();
        }
        ec.setUe(ue);
        EC nouvelleEC = ecService.create(ec);

        // Automatically assign EC to Enseignements for all Maquettes of the UE
        if (ue.getMaquettes() != null) {
            ue.getMaquettes().forEach(maquette -> {
                if(!enseignementService.Exist(nouvelleEC.getId(),maquette.getId())){
                    Enseignement enseignement = new Enseignement();
                    enseignement.setMaquette(maquette);
                    enseignement.setEc(nouvelleEC);
                    enseignementService.create(enseignement);
                }
                });
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nouvelleEC);
    }

    @PutMapping("/{id}/modifier")
    public ResponseEntity<EC> modifierEc(@PathVariable Long id, @RequestBody EC ec, @RequestParam(required = false) Long idUE) {
        EC ecUpdate = ecService.findById(id);
        if (ecUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        // Update fields conditionally
        if (idUE != null) {
            UE ue = ueService.findById(idUE);
            if (ue != null) {
                ecUpdate.setUe(ue);
            }
        }

        // Only update fields if the values have changed
        if (!ecUpdate.getCode().equals(ec.getCode())) {
            ecUpdate.setCode(ec.getCode());
        }
        if (!ecUpdate.getIntitule().equals(ec.getIntitule())) {
            ecUpdate.setIntitule(ec.getIntitule());
        }
        if (ecUpdate.getCM() != ec.getCM()) {
            ecUpdate.setCM(ec.getCM());
        }
        if (ecUpdate.getTP() != ec.getTP()) {
            ecUpdate.setTP(ec.getTP());
        }
        if (ecUpdate.getTD() != ec.getTD()) {
            ecUpdate.setTD(ec.getTD());
        }
        if (ecUpdate.getCoefficient() != ec.getCoefficient()) {
            ecUpdate.setCoefficient(ec.getCoefficient());
        }

        // Save the updated EC and assign it to Enseignements if necessary
        EC ec1 = ecService.update(ecUpdate);
        if (idUE != null) {
            UE ue = ueService.findById(idUE);
            if (ue.getMaquettes() != null) {
                ue.getMaquettes().forEach(maquette -> {
                    if(!enseignementService.Exist(ec1.getId(),maquette.getId())){
                        Enseignement enseignement = new Enseignement();
                        enseignement.setMaquette(maquette);
                        enseignement.setEc(ec1);
                        enseignementService.create(enseignement);
                    }
                });
            }
        }
        return ResponseEntity.ok(ec1);
    }

    @DeleteMapping("/{id}/supprimer")
    public ResponseEntity<Void> supprimerEC(@PathVariable Long id) {
        EC ec = ecService.findById(id);
        if (ec == null) {
            return ResponseEntity.notFound().build();
        }
        ecService.delete(ec);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/archiver")
    public ResponseEntity<Void> archiver(@PathVariable Long id) {
        EC ec = ecService.findById(id);
        if (ec == null) {
            return ResponseEntity.notFound().build();
        }
        ecService.archiver(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/activer")
    public ResponseEntity<Void> activer(@PathVariable Long id) {
        EC ec = ecService.findById(id);
        if (ec == null) {
            return ResponseEntity.notFound().build();
        }
        ecService.activer(id);
        return ResponseEntity.ok().build();
    }
}
