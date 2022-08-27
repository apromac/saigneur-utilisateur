package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.UtilisateurEntity;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    public Optional<UtilisateurEntity> findByUtilisateurID(Long utilisateurID);
    public List<UtilisateurEntity>findAllUtilisateur();
    public List<UtilisateurEntity> findByProfil(Long profilID);
}
