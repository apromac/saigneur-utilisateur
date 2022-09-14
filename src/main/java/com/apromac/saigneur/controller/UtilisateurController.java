package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.service.ProfilService;
import com.apromac.saigneur.service.UtilisateurService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ProfilService profilService;

    @ApiOperation(value = "Méthode permettant de récupérer un utilisateur grace à son ID")
    @GetMapping(value = "/utilisateur/findByUtilisateurID/{utilisateurID}")
    public ResponseEntity<UtilisateurEntity> recupererUnUtilisateur(@PathVariable long utilisateurID) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurService.findByUtilisateurID(utilisateurID);

        return new ResponseEntity<>(utilisateurOptional.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Méthode permettant de récupérer la liste des utilisateurs")
    @GetMapping(value = "/utilisateur/findAllUtilisateur")
    public ResponseEntity<List<UtilisateurEntity>> recupererUtilisateurs() {
        List<UtilisateurEntity> utilisateurs = utilisateurService.findAllUtilisateur();

        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @ApiOperation(value = "Méthode permettant de récupérer la liste des utilisateurs grace à l'ID du profil")
    @GetMapping(value = "/utilisateur/findByProfil/{profilID}")
    public ResponseEntity<List<UtilisateurEntity>> recupererUtilisateurParProfil(@PathVariable long profilID) {
        List<UtilisateurEntity> profilUtilisateurs = utilisateurService.findByProfil(profilID);

        return new ResponseEntity<>(profilUtilisateurs, HttpStatus.OK);
    }

}
