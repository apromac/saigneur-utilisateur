package com.apromac.saigneur.service;

import com.apromac.saigneur.dto.UtilisateurDTO;
import com.apromac.saigneur.entity.UtilisateurEntity;

import java.util.List;

public interface UtilisateurService {
    public UtilisateurEntity saveUtilisateur(UtilisateurEntity utilisateur);
    public List<UtilisateurDTO> utilisateurDetails();
    public UtilisateurDTO authentification(String username, String password);
    public UtilisateurEntity updateUtilisateur(UtilisateurEntity utilisateurTrouver, UtilisateurEntity utilisateurEntity);
    public UtilisateurDTO findByUtilisateurDTO(Long utilisateurID);









    public UtilisateurEntity findByUtilisateurID(Long utilisateurID);
    public List<UtilisateurEntity>findAllUtilisateur();
}
