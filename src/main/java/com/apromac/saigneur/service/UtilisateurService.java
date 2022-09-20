package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.UtilisateurEntity;

import java.util.List;

public interface UtilisateurService {
    public UtilisateurEntity findByUtilisateurID(Long utilisateurID);
    public UtilisateurEntity saveUtilisateur(UtilisateurEntity utilisateur);
    public List<UtilisateurEntity>findAllUtilisateur();
    public List<UtilisateurEntity> findByProfil(Long profilID);
}
