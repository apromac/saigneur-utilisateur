package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.service.AccederService;
import com.apromac.saigneur.service.ProfilService;
import com.apromac.saigneur.utility.AccederRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AccederController {

    @Autowired
    AccederService accederService;

    @Autowired
    ProfilService profilService;


    @ApiOperation(value = "Méthode permettant de récupérer la liste des acces d'un profil grace à son ID")
    @GetMapping(value = "/acceder/profil/findByProfilID/{profilID}")
    public ResponseEntity<List<AccederEntity>> recupererAccesParProfil(@PathVariable long profilID) {
        ProfilEntity profil = profilService.findByProfilID(profilID);

        List<AccederEntity> accesProfils = accederService.findByProfil(profil);

        return new ResponseEntity<>(accesProfils, HttpStatus.OK);
    }


    @ApiOperation(value = "Méthode permettant de sauvegarder les accès d'un profil")
    @PostMapping(value = "/acceder/saveMenuByProfil")
    public ResponseEntity<List<AccederEntity>> sauvegarderUnAccesProfil(@RequestBody AccederRequest accederRequest) {
        ProfilEntity profilEntity = profilService.findByProfilID(accederRequest.getProfilID());

        List<AccederEntity> accederSave = accederService.saveMenuByProfil(profilEntity, accederRequest.getMenuIDs());

        return new ResponseEntity<>(accederSave, HttpStatus.CREATED);
    }
}
