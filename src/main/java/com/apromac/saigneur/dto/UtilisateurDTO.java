package com.apromac.saigneur.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtilisateurDTO {
    private Long utilisateurID;
    private String nomUtilisateur;
    private String prenomsUtilisateur;
    private String username;
    private String password;
    private String photoUtilisateur;
    private String profilActuel;
    private String posteActuel;
}
