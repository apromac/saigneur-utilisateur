package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.OccuperEntity;

import java.util.List;

public interface OccuperService {
    public List<OccuperEntity> findByUtilisateur(Long utilisateurID);
    public OccuperEntity findByUtilisateurAndIsOccuper(Long utilisateurID, Boolean isActif);
    public List<OccuperEntity> findByPoste(Long posteID);
    public OccuperEntity findByPosteAndIsOccuper(Long posteID, Boolean isActif);
}
