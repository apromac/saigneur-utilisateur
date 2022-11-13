package com.apromac.saigneur.controller;

import com.apromac.saigneur.dto.UtilisateurDTO;
import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.service.OccuperService;
import com.apromac.saigneur.service.UtilisateurService;
import com.apromac.saigneur.utility.AuthentificateRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private OccuperService occuperService;



    @ApiOperation(value = "Méthode permettant de récupérer la liste des utilisateurs (UtilisateurDTO) avec plus d'informations")
    @GetMapping(value = "/utilisateur/findByUtilisateurDTO")
    public ResponseEntity<List<UtilisateurDTO>> recupererUtilisateurDTO() {
        List<UtilisateurDTO> utilisateurDTOs = utilisateurService.findByUtilisateurDTO();

        return new ResponseEntity<>(utilisateurDTOs, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant à un utilisateur de s'authentifier et de recupérer toutes informations " +
            "sur l'utilisateur connecté. Elle permet d'accéder au menu en fonction des droits que possèdent l'utilisateur")
    @PostMapping(value = "/utilisateur/auth")
    public ResponseEntity<UtilisateurDTO> authentification(@Validated @RequestBody AuthentificateRequest authentificateRequest) {
        UtilisateurDTO utilisateurAuth = utilisateurService.authentification(authentificateRequest.getUsername(),
                authentificateRequest.getPassword());

        return new ResponseEntity<>(utilisateurAuth, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de sauvegarder un utilisateur")
    @PostMapping(value = "/utilisateur/saveUtilisateur")
    public ResponseEntity<UtilisateurEntity> sauvegarderUnUtilisateur(@RequestBody UtilisateurEntity utilisateur) {
        UtilisateurEntity utilisateurSave = utilisateurService.saveUtilisateur(utilisateur);

        return new ResponseEntity<>(utilisateurSave, HttpStatus.CREATED);
    }



    @ApiOperation(value = "Méthode permettant de modifier un utilisateur grace à son ID et un objet utilisateur")
    @PutMapping(value = "/utilisateur/{utilisateurID}")
    public ResponseEntity<UtilisateurEntity> modifierUnUtilisateur(@RequestBody UtilisateurEntity utilisateurEntity,
                                                                   @PathVariable Long utilisateurID) {
        UtilisateurEntity utilisateurTrouver = utilisateurService.findByUtilisateurID(utilisateurID);

        UtilisateurEntity utilisateurUpdate = utilisateurService.updateUtilisateur(utilisateurTrouver, utilisateurEntity);

        return new ResponseEntity<>(utilisateurUpdate, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de supprimer un utilisateur grace à son ID")
    @DeleteMapping(value = "/utilisateur/{utilisateurID}")
    public ResponseEntity<Void> supprimerUnUtilisateur(@PathVariable Long utilisateurID) {
        UtilisateurEntity utilisateurTrouver = utilisateurService.findByUtilisateurID(utilisateurID);

        utilisateurService.deleteUtilisateur(utilisateurTrouver);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}












//    @ApiOperation(value = "Méthode permettant de récupérer les détails d'informations d'un utilisateur grace à son ID")
//    @GetMapping(value = "/utilisateur/findByUtilisateurDTO/{utilisateurID}")
//    public ResponseEntity<UtilisateurDTO> recupererUnUtilisateurDTO(@PathVariable long utilisateurID) {
//        UtilisateurDTO utilisateurDTO = utilisateurService.findByUtilisateurDTO(utilisateurID);
//
//        return new ResponseEntity<>(utilisateurDTO, HttpStatus.OK);
//    }
//
//
//    @ApiOperation(value = "Méthode permettant de récupérer un utilisateur grace à son ID")
//    @GetMapping(value = "/utilisateur/findByUtilisateurID/{utilisateurID}")
//    public ResponseEntity<UtilisateurEntity> recupererUnUtilisateur(@PathVariable long utilisateurID) {
//        UtilisateurEntity utilisateur = utilisateurService.findByUtilisateurID(utilisateurID);
//
//        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
//    }
//
//
//    @ApiOperation(value = "Méthode permettant de récupérer l'utilisateur grace à l'ID du poste")
//    @GetMapping(value = "/utilisateur/occuper/findByPoste/{posteID}")
//    public ResponseEntity<UtilisateurEntity> recupererUtilisateurParPoste(@PathVariable long posteID) {
//        OccuperEntity posteOccuperActif = occuperService.findByPosteAndIsOccuper(posteID, true);
//        if (posteOccuperActif == null)
//            throw new NotFoundException("Désolé, ce poste n'a pas d'occupant");
//
//        UtilisateurEntity utilisateur = posteOccuperActif.getUtilisateur();
//
//        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
//    }

