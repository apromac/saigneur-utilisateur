package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.PosteEntity;

import java.util.List;

public interface PosteService {

    /**
     * Methode permettant de récupérer la liste des postes.
     * @return postes
     */
    public List<PosteEntity> findAllPoste();

    /**
     * Methode permettant de récupérer un poste grace à son ID.
     * @param posteID
     * @return poste
     */
    public PosteEntity findByPosteID(Long posteID);

    /**
     * Methode permettant de sauvegarder un poste.
     * @param posteEntity
     * @return posteSave
     */
    public PosteEntity savePoste(PosteEntity posteEntity);

    /**
     * Methode permettant de modifier un poste grace au posteTrouver et au posteEntity.
     * @param posteTrouver
     * @param posteEntity
     * @return
     */
    public PosteEntity updatePoste(PosteEntity posteTrouver, PosteEntity posteEntity);

    /**
     * Methode permettant de récupérer la liste des postes grace à l'ID du profil.
     * @param profilID
     * @return
     */
    public List<PosteEntity> findByProfil(Long profilID);

    /**
     * Methode permettant de récupérer la liste des postes en fonction de l'ID du profil et le district
     * @param profilID
     * @param districtBean
     * @return
     */
    public List<PosteEntity> findByProfilAndDistrictBean(Long profilID, String districtBean);

}

