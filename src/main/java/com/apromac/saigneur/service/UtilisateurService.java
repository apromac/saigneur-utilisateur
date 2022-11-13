package com.apromac.saigneur.service;

import com.apromac.saigneur.dto.UtilisateurDTO;
import com.apromac.saigneur.entity.UtilisateurEntity;

import java.util.List;

public interface UtilisateurService {

    /**
     * Methode permettant de récupérer la liste des utilisateurs (UtilisateurDTO) contenant plus d'informations
     * @return
     */
    public List<UtilisateurDTO> findByUtilisateurDTO();


    /**
     * Methode permettant de s'authentifier à la plateforme grace à un username et un password. Elle permet d'accéder
     * au menu en fonction des droits que possèdent l'utilisateur
     * @param username
     * @param password
     * @return
     */
    public UtilisateurDTO authentification(String username, String password);


    /**
     * Methode permettant de sauvegarder un utilisateur.
     * @param utilisateur
     * @return
     */
    public UtilisateurEntity saveUtilisateur(UtilisateurEntity utilisateur);


    /**
     * Methode permettant de récupérer un utilisateur grace à son ID
     * @param utilisateurID
     * @return
     */
    public UtilisateurEntity findByUtilisateurID(Long utilisateurID);


    /**
     * Methode permettant de modifier un utilisateur grace à utilisateurTrouver et utilisateurEntity
     * @param utilisateurTrouver
     * @param utilisateurEntity
     * @return
     */
    public UtilisateurEntity updateUtilisateur(UtilisateurEntity utilisateurTrouver, UtilisateurEntity utilisateurEntity);

    /**
     * Methode permettant de supprimer un utilisateur à partir d'un objet UtilisateurEntity
     * @param utilisateurEntity
     */
    public void deleteUtilisateur(UtilisateurEntity utilisateurEntity);

}




//    public List<UtilisateurDTO> utilisateurDetails();
//    public UtilisateurDTO findByUtilisateurDTO(Long utilisateurID);
//    public List<UtilisateurEntity>findAllUtilisateur();