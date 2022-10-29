package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.OccuperEntity;

public interface OccuperService {

    /**
     * Methode permettant de sauvegarder un poste que occuper un utilisateur. Elle prend en argument un objet OccuperEntity
     * contenant le poste et l'utilisateur
     * @param occuperEntity
     * @return
     */
    public OccuperEntity saveOccuper(OccuperEntity occuperEntity);

}




//    public List<OccuperEntity> findByPoste(Long posteID);
//    public OccuperEntity findByPosteActuelTDH(Long posteTDHID);
//    public List<OccuperEntity> findByDistrictParProfil(String district, Long profilID);
//
////    public OccuperEntity saveOccuper(Long utilisateurID, Long posteID);
//
//    public List<OccuperEntity> findByUtilisateur(Long utilisateurID);
//    public OccuperEntity findByUtilisateurAndIsOccuper(Long utilisateurID, Boolean isActif);
//    public OccuperEntity findByPosteAndIsOccuper(Long posteID, Boolean isActif);
