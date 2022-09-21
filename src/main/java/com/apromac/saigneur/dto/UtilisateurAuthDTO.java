package com.apromac.saigneur.dto;

import lombok.Data;

@Data
public class UtilisateurAuthDTO {
    private Long utilisateurID;
    private String nomUtilisateur;
    private String prenomsUtilisateur;
    private String username;
    private String password;
    private String photoUtilisateur;
    private String profilActuel;
    private String posteActuel;
}
