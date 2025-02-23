package com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.controller;

import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.model.Classe;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionClasse.service.ClasseService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.model.EC;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesEC.service.ECService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.model.UE;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionDesUE.service.UEService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.model.Enseignement;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionEnseignement.service.EnseignementService;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.model.Maquette;
import com.uasz.sn.Gestion_Maquette.Gestion.Maquette.Microservice.GestionMaquette.service.MaquetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maquettes")
public class MaquetteController {

    @Autowired
    private MaquetteService maquetteService;
    @Autowired
    private UEService ueService;
    @Autowired
    private ClasseService classeService;
    @Autowired
    private ECService ecService;
    @Autowired
    private EnseignementService enseignementService;

    @GetMapping("")
    public ResponseEntity<List<Maquette>> afficherMaquette() {
        List<Maquette> maquettes = maquetteService.findAll();
        return new ResponseEntity<>(maquettes, HttpStatus.OK);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Maquette> ajouterMaquette(@RequestParam Long classe, @RequestParam Long[] idUEs, @RequestParam int semestre) {
        Maquette maquette = new Maquette();
        Classe classe1 = classeService.findById(classe);
        if (classe1 == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Handle error if class is not found
        }
        maquette.setNom(classe1.getFormation().getIntitule() + '-' + classe1.getNiveau());
        maquette.setActive(true);
        maquette.setArchive(false);
        maquette.setSemestre(semestre);
        maquette.setClasse(classe1);
        if (idUEs != null) {
            maquette.setUeList(ueService.findByIds(idUEs));
        }
        Maquette maquette1 = maquetteService.create(maquette);
        if(idUEs != null){
            List<UE> ues = ueService.findByIds(idUEs);
            ues.forEach(ue->{
                for(EC ec: ue.getEcList()){
                    if(!enseignementService.Exist(ec.getId(),maquette1.getId())){
                        Enseignement enseignement = new Enseignement();
                        enseignement.setEc(ec);
                        enseignement.setMaquette(maquette);
                        enseignementService.create(enseignement);
                    }
                }
            });
         }
        return ResponseEntity.status(HttpStatus.CREATED).body(maquette1);
    }

    @PostMapping("/Maquette/modifier")
    public ResponseEntity<Maquette> modifierMaquette(@RequestParam Long id, @RequestParam String nom, @RequestParam int semestre, @RequestParam Long[] idUEs) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Return 404 if maquette is not found
        }
        if (!maquette.getNom().equals(nom)) {
            maquette.setNom(nom);
        }
        if (maquette.getSemestre() != semestre) {
            maquette.setSemestre(semestre);
        }
        if (idUEs != null) {
            maquette.setUeList(ueService.findByIds(idUEs));
        }
        Maquette maquette1 = maquetteService.update(maquette);
        if(idUEs != null){
            List<UE> ues = ueService.findByIds(idUEs);
            ues.forEach(ue->{
                for(EC ec: ue.getEcList()){
                    if(!enseignementService.Exist(ec.getId(),maquette1.getId())){
                        Enseignement enseignement = new Enseignement();
                        enseignement.setEc(ec);
                        enseignement.setMaquette(maquette);
                        enseignementService.create(enseignement);
                    }
                }
            });
        }
        return new ResponseEntity<>(maquette1, HttpStatus.OK);
    }

    @PostMapping("/{id}/supprimer")
    public ResponseEntity<String> supprimerMaquette(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquette not found");
        }
        maquetteService.delete(maquette);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Maquette deleted");
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<List<UE>> detailMaquette(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<UE> listeUE = maquette.getUeList();
        return new ResponseEntity<>(listeUE, HttpStatus.OK);
    }

    @PostMapping("/{id}/archiver")
    public ResponseEntity<String> archiver(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquette not found");
        }
        maquetteService.archiver(id);
        return ResponseEntity.status(HttpStatus.OK).body("Maquette archived");
    }

    @PostMapping("/{id}/activer")
    public ResponseEntity<String> activer(@PathVariable Long id) {
        Maquette maquette = maquetteService.findById(id);
        if (maquette == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquette not found");
        }
        maquetteService.activer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Maquette activated");
    }
}
