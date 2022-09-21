package com.apromac.saigneur.service;

import com.apromac.saigneur.dto.UtilisateurDTO;
import com.apromac.saigneur.entity.UtilisateurEntity;

import java.util.List;

public interface UtilisateurService {
    public UtilisateurEntity findByUtilisateurID(Long utilisateurID);
    public UtilisateurEntity saveUtilisateur(UtilisateurEntity utilisateur);
    public UtilisateurDTO authentification(String username, String password);
    public List<UtilisateurDTO> utilisateurDetails();
    public List<UtilisateurEntity>findAllUtilisateur();
}
