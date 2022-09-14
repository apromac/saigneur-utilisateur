package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.service.PosteService;
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
public class PosteController {

    @Autowired
    private PosteService posteService;

    @ApiOperation(value = "Méthode permettant de récupérer un poste grace à son ID")
    @GetMapping(value = "/poste/findByPosteID/{posteID}")
    public ResponseEntity<PosteEntity> recupererUnPoste(@PathVariable long posteID) {
        Optional<PosteEntity> posteOptional = posteService.findByPosteID(posteID);

        return new ResponseEntity<>(posteOptional.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Méthode permettant de récupérer la liste des postes")
    @GetMapping(value = "/poste/findAllPoste")
    public ResponseEntity<List<PosteEntity>> recupererPostes() {
        List<PosteEntity> postes = posteService.findAllPoste();

        return new ResponseEntity<>(postes, HttpStatus.OK);
    }

}
