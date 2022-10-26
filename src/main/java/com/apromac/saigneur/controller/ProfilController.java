package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.service.ProfilService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ProfilController {

    @Autowired
    private ProfilService profilService;



    @ApiOperation(value = "Méthode permettant de récupérer la liste des profils")
    @GetMapping(value = "/profil/findAllProfil")
    public ResponseEntity<List<ProfilEntity>> recupererProfils() {
        List<ProfilEntity> profils = profilService.findAllProfil();

        return new ResponseEntity<>(profils, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de récupérer un profil grace à son ID")
    @GetMapping(value = "/profil/findByProfilID/{profilID}")
    public ResponseEntity<ProfilEntity> recupererUnProfil(@PathVariable long profilID) {
        ProfilEntity profil = profilService.findByProfilID(profilID);

        return new ResponseEntity<>(profil, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de modifier un profil grace à son ID")
    @PutMapping(value = "/profil/{profilID}")
    public ResponseEntity<ProfilEntity> modifierUnProfil(@RequestBody ProfilEntity profilEntity, @PathVariable Long profilID) {
        ProfilEntity profilTrouver = profilService.findByProfilID(profilID);

        ProfilEntity profilUpdate = profilService.updateProfil(profilTrouver, profilEntity);

        return new ResponseEntity<>(profilUpdate, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de sauvegarder un profil")
    @PostMapping(value = "/profil/saveProfil")
    public ResponseEntity<ProfilEntity> sauvegarderUnProfil(@RequestBody ProfilEntity profil) {
        ProfilEntity profilSave = profilService.saveProfil(profil);

        return new ResponseEntity<>(profilSave, HttpStatus.CREATED);
    }

}
