package com.apromac.saigneur.controller;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.MenuEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.service.AccederService;
import com.apromac.saigneur.service.MenuService;
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
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    ProfilService profilService;

    @Autowired
    AccederService accederService;




    @ApiOperation(value = "Méthode permettant de récupérer la liste des menus")
    @GetMapping(value = "/menu/findAllMenu")
    public ResponseEntity<List<MenuEntity>> recupererMenus() {
        List<MenuEntity> menus = menuService.findAllMenu();

        return new ResponseEntity<>(menus, HttpStatus.OK);
    }



    @ApiOperation(value = "Méthode permettant de récupérer la liste de menu d'un profil en fonction de l'ID du profil")
    @GetMapping(value = "/menu/profil/{profilID}")
    public ResponseEntity<List<MenuEntity>> recupererMenuParProfil(@PathVariable long profilID) {
        ProfilEntity profil = profilService.findByProfilID(profilID);

        List<AccederEntity> profilsAcceder = accederService.findByProfil(profil);

        List<MenuEntity> menus = menuService.findByAccesProfil(profilsAcceder);

        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
    
}
