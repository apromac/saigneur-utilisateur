package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.service.OccuperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class OccuperController {

    @Autowired
    private OccuperService occuperService;



    @ApiOperation(value = "Méthode permettant de sauvegarder un poste occupé par un utilisateur")
    @PostMapping(value = "/occuper/saveOccuper")
    public ResponseEntity<OccuperEntity> sauvegarderUnPosteOccuper(@RequestBody OccuperEntity occuperEntity) {
        OccuperEntity occuperSave = occuperService.saveOccuper(occuperEntity);

        return new ResponseEntity<>(occuperSave, HttpStatus.CREATED);
    }



    @ApiOperation(value = "Méthode permettant de recupérer un poste occupé par un utilisateur")
    @GetMapping(value = "/occuper/utilisateur/{utilisateurID}")
    public ResponseEntity<OccuperEntity> recupererUnPosteOccuper(@PathVariable Long utilisateurID) {
        OccuperEntity occuperTrouver = occuperService.findByUtilisateurAndIsOccuper(utilisateurID);

        return new ResponseEntity<>(occuperTrouver, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de modifier un poste occupé par un utilisateur")
    @PutMapping(value = "/occuper/{occuperID}")
    public ResponseEntity<OccuperEntity> modifierUnPosteOccuper(@PathVariable Long occuperID, @RequestBody OccuperEntity occuperEntity) {
        OccuperEntity occuperTrouver = occuperService.findByOccuperID(occuperID);

        OccuperEntity updateOccuper = occuperService.updateOccuper(occuperTrouver, occuperEntity);

        return new ResponseEntity<>(updateOccuper, HttpStatus.OK);
    }

}





//    @ApiOperation(value = "Méthode permettant de récupérer les informations relatives au poste actuel d'un TDH grace à l'ID du poste")
//    @GetMapping(value = "/occuper/findByPosteTDHID/{posteTDHID}")
//    public ResponseEntity<OccuperEntity> recupererPosteActuelTDHOccuper(@PathVariable long posteTDHID) {
//        OccuperEntity occuperEntity = occuperService.findByPosteActuelTDH(posteTDHID);
//
//        return new ResponseEntity<>(occuperEntity, HttpStatus.OK);
//    }
//
//
//    @ApiOperation(value = "Méthode permettant de récupérer la liste des profils TDH par district")
//    @GetMapping(value = "/occuper/district/{district}/profil/{profilID}")
//    public ResponseEntity<List<OccuperEntity>> recupererProfilTDHParDistrict(@PathVariable String district, @PathVariable Long profilID) {
//        List<OccuperEntity> occuperList = occuperService.findByDistrictParProfil(district, profilID);
//
//        return new ResponseEntity<>(occuperList, HttpStatus.OK);
//    }
//
//
//    @ApiOperation(value = "Méthode permettant de récupérer la liste des informations relatives au poste d'un utlisateur grace à l'ID du poste")
//    @GetMapping(value = "/occuper/findByPosteID/{posteID}")
//    public ResponseEntity<List<OccuperEntity>> recupererDetailsPoste(@PathVariable long posteID) {
//        List<OccuperEntity> occuperEntityList = occuperService.findByPoste(posteID);
//
//        return new ResponseEntity<>(occuperEntityList, HttpStatus.OK);
//    }