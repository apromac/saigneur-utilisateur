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




    @ApiOperation(value = "Méthode permettant de sauvegarder les droits d'accès menu d'un profil")
    @PostMapping(value = "/acceder/saveMenuByProfil")
    public ResponseEntity<List<AccederEntity>> sauvegarderUnAccesProfil(@RequestBody AccederRequest accederRequest) {
        ProfilEntity profilEntity = profilService.findByProfilID(accederRequest.getProfilID());

        List<AccederEntity> accederSave = accederService.saveMenuByProfil(profilEntity, accederRequest.getMenuIDs());

        return new ResponseEntity<>(accederSave, HttpStatus.CREATED);
    }



    @ApiOperation(value = "Méthode permettant de récupérer la liste des acces menu d'un profil grace à l'ID du profil")
    @GetMapping(value = "/acceder/profil/{profilID}")
    public ResponseEntity<List<AccederEntity>> recupererAccesParProfil(@PathVariable long profilID) {
        ProfilEntity profil = profilService.findByProfilID(profilID);

        List<AccederEntity> accesProfils = accederService.findByProfil(profil);

        return new ResponseEntity<>(accesProfils, HttpStatus.OK);
    }

}
