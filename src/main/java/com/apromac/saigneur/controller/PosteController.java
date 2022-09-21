package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.PosteEntity;
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


    @ApiOperation(value = "Méthode permettant de récupérer un poste grace à son ID")
    @GetMapping(value = "/poste/findByPosteID/{posteID}")
    public ResponseEntity<PosteEntity> recupererUnPoste(@PathVariable long posteID) {
        PosteEntity poste = posteService.findByPosteID(posteID);

        return new ResponseEntity<>(poste, HttpStatus.OK);
    }

    @ApiOperation(value = "Méthode permettant de récupérer la liste des postes d'un profil grace à son ID")
    @GetMapping(value = "/poste/findByProfil/{profilID}")
    public ResponseEntity<List<PosteEntity>> recupererPosteParProfil(@PathVariable long profilID) {
        List<PosteEntity> profilPostes = posteService.findByProfil(profilID);

        return new ResponseEntity<>(profilPostes, HttpStatus.OK);
    }

    @ApiOperation(value = "Méthode permettant de sauvegarder un poste")
    @PostMapping(value = "/poste/savePoste")
    public ResponseEntity<PosteEntity> sauvegarderUnPoste(@RequestBody PosteEntity poste) {
        PosteEntity posteSave = posteService.savePoste(poste);

        return new ResponseEntity<>(posteSave, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Méthode permettant de récupérer la liste des postes")
    @GetMapping(value = "/poste/findAllPoste")
    public ResponseEntity<List<PosteEntity>> recupererPostes() {
        List<PosteEntity> postes = posteService.findAllPoste();

        return new ResponseEntity<>(postes, HttpStatus.OK);
    }

}
