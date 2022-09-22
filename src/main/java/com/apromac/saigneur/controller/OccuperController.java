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
    public ResponseEntity<OccuperEntity> sauvegarderUnPosteOccuper(@PathVariable Long utilisateurID, @PathVariable Long posteID) {
        OccuperEntity occuperSave = occuperService.saveOccuper(utilisateurID, posteID);

        return new ResponseEntity<>(occuperSave, HttpStatus.CREATED);
    }
}
