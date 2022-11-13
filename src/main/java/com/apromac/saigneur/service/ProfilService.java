package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.ProfilEntity;

import java.util.List;

public interface ProfilService {

    /**
     * Methode permettant de récupérer la liste des profils.
     * @return profils
     */
    public List<ProfilEntity> findAllProfil();

    /**
     * Methode permettant de récupérer un profil grace à son ID
     * @param profilID
     * @return profil
     */
    public ProfilEntity findByProfilID(Long profilID);

    /**
     * Methode permettant de modifier un profil.
     * @param profilTrouver
     * @param profilEntity
     * @return
     */
    public ProfilEntity updateProfil(ProfilEntity profilTrouver, ProfilEntity profilEntity);

    /**
     * Methode permettant de sauvegarder un profil
     * @param profil
     * @return
     */
    public ProfilEntity saveProfil(ProfilEntity profil);

    /**
     * Methode permmettant de supprimer un profil grace à son ID
     * @param profilEntity
     */
    public void deleteProfil(ProfilEntity profilEntity);

}
