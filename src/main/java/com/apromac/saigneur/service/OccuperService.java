package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.OccuperEntity;

import java.util.List;

public interface OccuperService {
    public List<OccuperEntity> findByPoste(Long posteID);
    public OccuperEntity findByPosteActuelTDH(Long posteTDHID);
    public OccuperEntity saveOccuper(OccuperEntity occuperEntity);






















//    public OccuperEntity saveOccuper(Long utilisateurID, Long posteID);


    public List<OccuperEntity> findByUtilisateur(Long utilisateurID);
    public OccuperEntity findByUtilisateurAndIsOccuper(Long utilisateurID, Boolean isActif);
    public OccuperEntity findByPosteAndIsOccuper(Long posteID, Boolean isActif);

}
