package com.apromac.saigneur.dto;

import com.apromac.saigneur.entity.MenuEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UtilisateurDTO {
    private Long utilisateurID;
    private String nomUtilisateur;
    private String prenomsUtilisateur;
    private String username;
    private String password;
    private String telephoneUtilisateur;
    private String photoUtilisateur;
    private String profilActuel;
    private String posteActuel;
    private String district;
    private List<MenuEntity> menus;
}
