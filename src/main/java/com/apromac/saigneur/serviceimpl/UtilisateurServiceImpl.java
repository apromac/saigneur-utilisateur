package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.dto.UtilisateurDTO;
import com.apromac.saigneur.entity.*;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.exception.NotAcceptableException;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.repository.AccederRepository;
import com.apromac.saigneur.repository.OccuperRepository;
import com.apromac.saigneur.repository.UtilisateurRepository;
import com.apromac.saigneur.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private OccuperRepository occuperRepository;

    @Autowired
    private AccederRepository accederRepository;




    /**
     * Methode permettant de récupérer la liste des utilisateurs (UtilisateurDTO) avec plus d'informations. A savoir,
     * le poste, le district et la zone de l'utilisateur.
     * @return utilisateurDTOs
     */
    @Override
    public List<UtilisateurDTO> findByUtilisateurDTO() {
        List<UtilisateurEntity> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty())
            throw new NoContentException("Désolé, aucun utilisateur disponible.");

        List<UtilisateurDTO> utilisateurDTOs = new ArrayList<>();

        for (UtilisateurEntity utilisateur: utilisateurs) {
            UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

            List<OccuperEntity> occuperList = occuperRepository.findByUtilisateur(utilisateur);
            if (occuperList.isEmpty()) {
                utilisateurDTO.setUtilisateurID(utilisateur.getUtilisateurID());
                utilisateurDTO.setNomUtilisateur(utilisateur.getNomUtilisateur());
                utilisateurDTO.setPrenomsUtilisateur(utilisateur.getPrenomsUtilisateur());
                utilisateurDTO.setUsername(utilisateur.getUsername());
                utilisateurDTO.setPassword(utilisateur.getPassword());
                utilisateurDTO.setTelephoneUtilisateur(utilisateur.getTelephoneUtilisateur());
                utilisateurDTO.setPhotoUtilisateur(utilisateur.getPhotoUtilisateur());
                utilisateurDTO.setPosteActuel("");
                utilisateurDTO.setProfilActuel("");
                utilisateurDTO.setDistrict("");
            } else {
                for (OccuperEntity occuperEntity: occuperList) {
                    if (occuperEntity.getIsOccuper()) {
                        utilisateurDTO.setUtilisateurID(occuperEntity.getUtilisateur().getUtilisateurID());
                        utilisateurDTO.setNomUtilisateur(occuperEntity.getUtilisateur().getNomUtilisateur());
                        utilisateurDTO.setPrenomsUtilisateur(occuperEntity.getUtilisateur().getPrenomsUtilisateur());
                        utilisateurDTO.setUsername(occuperEntity.getUtilisateur().getUsername());
                        utilisateurDTO.setPassword(occuperEntity.getUtilisateur().getPassword());
                        utilisateurDTO.setTelephoneUtilisateur(occuperEntity.getUtilisateur().getTelephoneUtilisateur());
                        utilisateurDTO.setPhotoUtilisateur(occuperEntity.getUtilisateur().getPhotoUtilisateur());
                        utilisateurDTO.setPosteActuel(occuperEntity.getPoste().getLibellePoste());
                        utilisateurDTO.setProfilActuel(occuperEntity.getPoste().getProfil().getLibelleProfil());
                        utilisateurDTO.setDistrict(occuperEntity.getDistrictOccuper());
                    }
                }
            }

            utilisateurDTOs.add(utilisateurDTO);
        }

        return utilisateurDTOs;
    }



    /**
     * Methode permettant de s'authentifier à la plateforme. Elle permet d'accéder au menu en fonction des droits
     * que possèdent l'utilisateur.
     * @param username represente le login de l'utilisateur (String)
     * @param password resperente le mot de passe de l'utilisateur (String)
     * @return
     */
    @Override
    public UtilisateurDTO authentification(String username, String password) {
        UtilisateurEntity utilisateurAuthentifier = utilisateurRepository.findByUsernameAndPassword(username, password);
        if (utilisateurAuthentifier == null)
            throw new NotFoundException("Veuillez vérifier vos identifiants et réessayer.");

//        OccuperEntity posteUtilisateurOccuper = occuperRepository.findByUtilisateurAndIsOccuper(utilisateurAuthentifier, true);
        OccuperEntity posteUtilisateurOccuper = occuperRepository.findByUtilisateurAndIsOccuperTrue(utilisateurAuthentifier);
        if (posteUtilisateurOccuper == null)
            throw new NotFoundException("Désolé, aucun poste ne vous a été attribué. Veuillez contacter l'administrateur.");

        ProfilEntity profilEntity = posteUtilisateurOccuper.getPoste().getProfil();

        List<AccederEntity> acceders = accederRepository.findByProfil(profilEntity);
        if (acceders.isEmpty() || acceders == null)
            throw new NotFoundException("Désolé, vous ne posseder pas de droit pour acceder à la plateforme, veuillez contacter l'administrateur.");

        List<MenuEntity> menus = new ArrayList<>();
        for (AccederEntity acces : acceders) {
            MenuEntity menu = acces.getMenu(); // cré une exception lorsque il n'y a pas de menu pour ce profil
            menus.add(menu);
        }

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setUtilisateurID(posteUtilisateurOccuper.getUtilisateur().getUtilisateurID());
        utilisateurDTO.setNomUtilisateur(posteUtilisateurOccuper.getUtilisateur().getNomUtilisateur());
        utilisateurDTO.setPrenomsUtilisateur(posteUtilisateurOccuper.getUtilisateur().getPrenomsUtilisateur());
        utilisateurDTO.setUsername(posteUtilisateurOccuper.getUtilisateur().getUsername());
        utilisateurDTO.setPassword(posteUtilisateurOccuper.getUtilisateur().getPassword());
        utilisateurDTO.setTelephoneUtilisateur(posteUtilisateurOccuper.getUtilisateur().getTelephoneUtilisateur());
        utilisateurDTO.setPhotoUtilisateur(posteUtilisateurOccuper.getUtilisateur().getPhotoUtilisateur());
        utilisateurDTO.setPosteActuel(posteUtilisateurOccuper.getPoste().getLibellePoste());
        utilisateurDTO.setProfilActuel(posteUtilisateurOccuper.getPoste().getProfil().getLibelleProfil());
        utilisateurDTO.setDistrict(posteUtilisateurOccuper.getDistrictOccuper());
        utilisateurDTO.setZone(posteUtilisateurOccuper.getZoneOccuper());
        utilisateurDTO.setMenus(menus);

        return utilisateurDTO;
    }



    /**
     * Methode permettant de sauvegarder un utilisateur.
     * @param utilisateur represente un objet utilisateur.
     * @return
     */
    @Override
    public UtilisateurEntity saveUtilisateur(UtilisateurEntity utilisateur) {
        utilisateur.setNomUtilisateur(utilisateur.getNomUtilisateur().toUpperCase());
        utilisateur.setPrenomsUtilisateur(utilisateur.getPrenomsUtilisateur().toUpperCase());
        UtilisateurEntity utilisateurSave = utilisateurRepository.save(utilisateur);
        if (utilisateurSave == null)
            throw new RuntimeException("Une erreur est survenu lors de la sauvegarde de l'utilisateur.");

        return utilisateurSave;
    }



    /**
     * Methode permettant de récupérer un utilisateur grace à son ID
     * @param utilisateurID répresente l'ID de l'utilisateur
     * @return
     */
    @Override
    public UtilisateurEntity findByUtilisateurID(Long utilisateurID) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);
        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, l'utilisateur désigné n'existe pas");

        return utilisateurOptional.get();
    }



    /**
     * Methode permettant de modifier les informations d'un utilisateur grace à utilisateurTrouver et utilisateurEntity
     * @param utilisateurTrouver est un objet Utilisateur provenant de la base de données
     * @param utilisateurEntity est un objet Utilisateur provenant de la partie cliente
     * @return utilisateurUpdate
     */
    @Override
    public UtilisateurEntity updateUtilisateur(UtilisateurEntity utilisateurTrouver, UtilisateurEntity utilisateurEntity) {
        utilisateurTrouver.setNomUtilisateur(utilisateurEntity.getNomUtilisateur().toUpperCase());
        utilisateurTrouver.setPrenomsUtilisateur(utilisateurEntity.getPrenomsUtilisateur().toUpperCase());
        utilisateurTrouver.setUsername(utilisateurEntity.getUsername());
        utilisateurTrouver.setPassword(utilisateurEntity.getPassword());
        utilisateurTrouver.setTelephoneUtilisateur(utilisateurEntity.getTelephoneUtilisateur());
        utilisateurTrouver.setPhotoUtilisateur(utilisateurEntity.getPhotoUtilisateur());

        UtilisateurEntity utilisateurUpdate = utilisateurRepository.saveAndFlush(utilisateurTrouver);
        if (utilisateurUpdate == null)
            throw new RuntimeException("Désolé, nous avons rencontré une erreur lors de la mise à jour des données de l'utilisateur");

        return utilisateurUpdate;
    }


    /**
     * Methode permettant de supprimer un utilisateur à partir d'un objet UtilisateurEntity.
     * on supprime l'utilisateur lorsque ce dernier n'a pas de poste
     * @param utilisateurEntity
     */
    public void deleteUtilisateur(UtilisateurEntity utilisateurEntity) {
        List<OccuperEntity> posteOccuperParUtilisateur = occuperRepository.findByUtilisateur(utilisateurEntity);
        if (posteOccuperParUtilisateur.isEmpty()) {
            utilisateurRepository.delete(utilisateurEntity);
        } else {
            throw new NotAcceptableException("Désolé, ce utilisateur ne peut être supprimé car il est rattaché à une autre entité.");
        }

    }


}

