package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.dto.UtilisateurDTO;
import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.exception.NotFoundException;
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



    /**
     *
     * @param utilisateur
     * @return
     */
    public UtilisateurEntity saveUtilisateur(UtilisateurEntity utilisateur) {
        UtilisateurEntity utilisateurSave = utilisateurRepository.save(utilisateur);
        if (utilisateurSave == null)
            throw new RuntimeException("Une erreur est survenu lors de la sauvegarde de l'utilisateur.");

        return utilisateurSave;
    }


    /**
     *
     * @return
     */
    public List<UtilisateurDTO> utilisateurDetails() {
        List<UtilisateurEntity> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty())
            throw new NoContentException("Désolé, la liste utilisateur est vide.");

        List<UtilisateurDTO> utilisateurDetails = new ArrayList<>();

        for (UtilisateurEntity utilisateur: utilisateurs) {
            UtilisateurDTO utilisateurDTO = new UtilisateurDTO();

            List<OccuperEntity> occuperEntityList = occuperRepository.findByUtilisateur(utilisateur);
            if (occuperEntityList.isEmpty()) {
                utilisateurDTO.setUtilisateurID(utilisateur.getUtilisateurID());
                utilisateurDTO.setNomUtilisateur(utilisateur.getNomUtilisateur());
                utilisateurDTO.setPrenomsUtilisateur(utilisateur.getPrenomsUtilisateur());
                utilisateurDTO.setUsername(utilisateur.getUsername());
                utilisateurDTO.setPassword(utilisateur.getPassword());
                utilisateurDTO.setTelephoneUtilisateur(utilisateur.getTelephoneUtilisateur());
                utilisateurDTO.setPhotoUtilisateur(utilisateur.getPhotoUtilisateur());
                utilisateurDTO.setPosteActuel("");
                utilisateurDTO.setProfilActuel("");
            } else {
                for (OccuperEntity occuperEntity: occuperEntityList) {
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
                    }
                }
            }

            utilisateurDetails.add(utilisateurDTO);
        }

        return utilisateurDetails;
    }


    /**
     *
     * @param username
     * @param password
     * @return
     */
    public UtilisateurDTO authentification(String username, String password) {
        UtilisateurEntity utilisateurAuthentifier = utilisateurRepository.findByUsernameAndPassword(username, password);
        if (utilisateurAuthentifier == null)
            throw new NotFoundException("Une erreur est survenu lors de l'authentification de l'utilisateur.");

        OccuperEntity posteUtilisateur = occuperRepository.findByUtilisateurAndIsOccuperTrue(utilisateurAuthentifier);
        if (posteUtilisateur == null)
            throw new NotFoundException("Désolé, nous avons rencontré un problème lors de la synchronisation des données");

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setUtilisateurID(posteUtilisateur.getUtilisateur().getUtilisateurID());
        utilisateurDTO.setNomUtilisateur(posteUtilisateur.getUtilisateur().getNomUtilisateur());
        utilisateurDTO.setPrenomsUtilisateur(posteUtilisateur.getUtilisateur().getPrenomsUtilisateur());
        utilisateurDTO.setUsername(posteUtilisateur.getUtilisateur().getUsername());
        utilisateurDTO.setPassword(posteUtilisateur.getUtilisateur().getPassword());
        utilisateurDTO.setTelephoneUtilisateur(posteUtilisateur.getUtilisateur().getTelephoneUtilisateur());
        utilisateurDTO.setPhotoUtilisateur(posteUtilisateur.getUtilisateur().getPhotoUtilisateur());
        utilisateurDTO.setPosteActuel(posteUtilisateur.getPoste().getLibellePoste());
        utilisateurDTO.setProfilActuel(posteUtilisateur.getPoste().getProfil().getLibelleProfil());

        return utilisateurDTO;
    }


    /**
     *
     * @param utilisateurTrouver
     * @param utilisateurEntity
     * @return
     */
    public UtilisateurEntity updateUtilisateur(UtilisateurEntity utilisateurTrouver, UtilisateurEntity utilisateurEntity) {

        utilisateurTrouver.setNomUtilisateur(utilisateurEntity.getNomUtilisateur());
        utilisateurTrouver.setPrenomsUtilisateur(utilisateurEntity.getPrenomsUtilisateur());
        utilisateurTrouver.setUsername(utilisateurEntity.getUsername());
        utilisateurTrouver.setPassword(utilisateurEntity.getPassword());
        utilisateurTrouver.setTelephoneUtilisateur(utilisateurEntity.getTelephoneUtilisateur());
        utilisateurTrouver.setPhotoUtilisateur(utilisateurEntity.getPhotoUtilisateur());

        UtilisateurEntity utilisateurSave = utilisateurRepository.saveAndFlush(utilisateurTrouver);
        if (utilisateurSave == null)
            throw new RuntimeException("Désolé, nous avons rencontré une ereur lors de la mise à jour des données de l'utilisateur");

        return utilisateurSave;
    }



    /**
     *
     * @param utilisateurID
     * @return
     */
    public UtilisateurDTO findByUtilisateurDTO(Long utilisateurID) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);
        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, cet utilisateur n'existe pas dans la base.");

        OccuperEntity occuperEntity = occuperRepository.findByUtilisateurAndIsOccuperTrue(utilisateurOptional.get());
        if (occuperEntity == null)
            throw new NotFoundException("Désolé, nous n'avons pas pu recupérer les informations de l'utilisateur");

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setNomUtilisateur(occuperEntity.getUtilisateur().getNomUtilisateur());
        utilisateurDTO.setPrenomsUtilisateur(occuperEntity.getUtilisateur().getPrenomsUtilisateur());
        utilisateurDTO.setUsername(occuperEntity.getUtilisateur().getUsername());
        utilisateurDTO.setPassword("");
        utilisateurDTO.setTelephoneUtilisateur(occuperEntity.getUtilisateur().getTelephoneUtilisateur());
        utilisateurDTO.setPhotoUtilisateur(occuperEntity.getUtilisateur().getPhotoUtilisateur());
        utilisateurDTO.setPosteActuel(occuperEntity.getPoste().getLibellePoste());
        utilisateurDTO.setProfilActuel(occuperEntity.getPoste().getProfil().getLibelleProfil());

        return utilisateurDTO;
    }




































    /**
     *
     * @param utilisateurID
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
     *
     * @return
     */
    @Override
    public List<UtilisateurEntity> findAllUtilisateur() {
        List<UtilisateurEntity> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty())
            throw new NoContentException("Désolé, aucun utilisateur disponible");

        return utilisateurs;
    }

}
