package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.service.OccuperService;
import com.apromac.saigneur.service.PosteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PosteController {

    @Autowired
    private PosteService posteService;

    @Autowired
    private OccuperService occuperService;




    @ApiOperation(value = "Méthode permettant de récupérer la liste des postes")
    @GetMapping(value = "/poste/findAllPoste")
    public ResponseEntity<List<PosteEntity>> recupererPostes() {
        List<PosteEntity> postes = posteService.findAllPoste();

        return new ResponseEntity<>(postes, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de récupérer un poste grace à son ID")
    @GetMapping(value = "/poste/{posteID}")
    public ResponseEntity<PosteEntity> recupererUnPoste(@PathVariable long posteID) {
        PosteEntity poste = posteService.findByPosteID(posteID);

        return new ResponseEntity<>(poste, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de sauvegarder un poste")
    @PostMapping(value = "/poste/savePoste")
    public ResponseEntity<PosteEntity> sauvegarderUnPoste(@RequestBody PosteEntity posteEntity) {
        PosteEntity posteSave = posteService.savePoste(posteEntity);

        return new ResponseEntity<>(posteSave, HttpStatus.CREATED);
    }



    @ApiOperation(value = "Méthode permettant de modifier un poste garce à son ID et un objet poste")
    @PutMapping(value = "/poste/{posteID}")
    public ResponseEntity<PosteEntity> modifierUnPoste(@RequestBody PosteEntity posteEntity, @PathVariable long posteID) {
        PosteEntity posteTrouver = posteService.findByPosteID(posteID);

        PosteEntity posteUpdate = posteService.updatePoste(posteTrouver, posteEntity);

        return new ResponseEntity<>(posteUpdate, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de récupérer la liste des postes d'un profil grace à l'ID du profil")
    @GetMapping(value = "/poste/profil/{profilID}")
    public ResponseEntity<List<PosteEntity>> recupererPostesParProfil(@PathVariable long profilID) {
        List<PosteEntity> profilPostes = posteService.findByProfil(profilID);

        return new ResponseEntity<>(profilPostes, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de récupérer la liste des postes par rapport à un profil et un district")
    @GetMapping(value = "/poste/profil/{profilID}/district/{libelleDistrict}")
    public ResponseEntity<List<PosteEntity>> recupererPostesParDistrict(@PathVariable Long profilID, @PathVariable String libelleDistrict) {
        List<PosteEntity> profilPostes = posteService.findByProfilAndDistrictBean(profilID, libelleDistrict);

        return new ResponseEntity<>(profilPostes, HttpStatus.OK);
    }


    @ApiOperation(value = "Méthode permettant de supprimer un poste grace à son ID")
    @DeleteMapping(value = "/poste/{posteID}")
    public ResponseEntity<?> supprimerUnPoste(@PathVariable Long posteID) {
        PosteEntity posteTrouver = posteService.findByPosteID(posteID);

        posteService.deletePoste(posteTrouver);

        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}



//    @ApiOperation(value = "Méthode permettant de récupérer la liste des postes d'un profil grace à son ID")
//    @GetMapping(value = "/poste/findByPosteDTO/{posteID}")
//    public ResponseEntity<PosteDTO> recupererPosteDTO(@PathVariable long posteID) {
//        PosteDTO posteDTO = posteService.findByPosteDTO(posteID);
//
//        return new ResponseEntity<>(posteDTO, HttpStatus.OK);
//    }



