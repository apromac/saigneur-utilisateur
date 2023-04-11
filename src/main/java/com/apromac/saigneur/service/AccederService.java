package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.ProfilEntity;

import java.util.List;

public interface AccederService {

    /**
     * Méthode permettant de sauvegarder les droits d'accès menu d'un profil en fonction d'un objet Profil et d'une
     * liste d'ID de menu
     * @param profilEntity
     * @param menuIDs
     */
    public List<AccederEntity> saveMenuByProfil(ProfilEntity profilEntity, List<Long> menuIDs);

    /**
     * Methode permettant de récupérer la liste des accès menu d'un profil
     * @param profilEntity
     * @return
     */
    public List<AccederEntity> findByProfil(ProfilEntity profilEntity);

}
