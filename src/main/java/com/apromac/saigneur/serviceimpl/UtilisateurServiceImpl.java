package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.dto.UtilisateurDTO;
import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
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
     * @param username
     * @param password
     * @return
     */
    public UtilisateurDTO authentification(String username, String password) {
        UtilisateurEntity utilisateurAuthentifier = utilisateurRepository.findByUsernameAndPassword(username, password);
        if (utilisateurAuthentifier == null)
            throw new RuntimeException("Une erreur est survenu lors de l'authentification de l'utilisateur.");

        OccuperEntity posteUtilisateur = occuperRepository.findByUtilisateurAndIsOccuperTrue(utilisateurAuthentifier);
        if (posteUtilisateur == null)
            throw new RuntimeException("Désolé, nous avons rencontré un problème lors de la synchronisation des données");

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setUtilisateurID(posteUtilisateur.getUtilisateur().getUtilisateurID());
        utilisateurDTO.setNomUtilisateur(posteUtilisateur.getUtilisateur().getNomUtilisateur());
        utilisateurDTO.setPrenomsUtilisateur(posteUtilisateur.getUtilisateur().getPrenomsUtilisateur());
        utilisateurDTO.setUsername(posteUtilisateur.getUtilisateur().getUsername());
        utilisateurDTO.setPassword(posteUtilisateur.getUtilisateur().getPassword());
        utilisateurDTO.setPhotoUtilisateur(posteUtilisateur.getUtilisateur().getPhotoUtilisateur());
        utilisateurDTO.setPosteActuel(posteUtilisateur.getPoste().getLibellePoste());
        utilisateurDTO.setProfilActuel(posteUtilisateur.getPoste().getProfil().getLibelleProfil());

        return utilisateurDTO;
    }

    /**
     *
     * @return
     */
    public List<UtilisateurDTO> utilisateurDetails() {
        List<UtilisateurEntity> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty())
            throw new RuntimeException("Désolé, la liste utilisateur est vide.");

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
     * @return
     */
    @Override
    public List<UtilisateurEntity> findAllUtilisateur() {
        List<UtilisateurEntity> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty())
            throw new NotFoundException("Désolé, aucun utilisateur disponible");

        return utilisateurs;
    }

}

//
//    /**
//     *
//     * @return
//     */
//    public List<UtilisateurDTO> utilisateurDetails() {
//        List<UtilisateurEntity> utilisateurs = utilisateurRepository.findAll();
//        if (utilisateurs.isEmpty())
//            throw new RuntimeException("Désolé, la liste utilisateur est vide.");
//
//        List<UtilisateurDTO> utilisateurDetails = new ArrayList<>();
//
//        for (UtilisateurEntity utilisateur: utilisateurs) {
//            UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
//
////            OccuperEntity utilisateurPosteOccuper = occuperRepository.findByUtilisateurAndIsOccuperTrue(utilisateur);
////            if (utilisateurPosteOccuper == null) {
////                throw new RuntimeException("Désolé, nous ne parvenons pas à satisfaire votre demande. Reéssayez");
////            }
//
//            List<OccuperEntity> occuperEntityList = occuperRepository.findByUtilisateur(utilisateur);
//            if (occuperEntityList.isEmpty())
//                continue;
////                throw new RuntimeException("Désolé, cet utilisateur n'a jamain obtenu de poste.");
//
//            for (OccuperEntity occuperEntity: occuperEntityList) {
//                if (occuperEntity.getIsOccuper()) {
//                    utilisateurDTO.setUtilisateurID(occuperEntity.getUtilisateur().getUtilisateurID());
//                    utilisateurDTO.setNomUtilisateur(occuperEntity.getUtilisateur().getNomUtilisateur());
//                    utilisateurDTO.setPrenomsUtilisateur(occuperEntity.getUtilisateur().getPrenomsUtilisateur());
//                    utilisateurDTO.setUsername(occuperEntity.getUtilisateur().getUsername());
//                    utilisateurDTO.setPassword(occuperEntity.getUtilisateur().getPassword());
//                    utilisateurDTO.setPhotoUtilisateur(occuperEntity.getUtilisateur().getPhotoUtilisateur());
//                    utilisateurDTO.setPosteActuel(occuperEntity.getPoste().getLibellePoste());
//                    utilisateurDTO.setProfilActuel(occuperEntity.getPoste().getProfil().getLibelleProfil());
//                }
//
//            }
//
//            utilisateurDetails.add(utilisateurDTO);
//
//        }
//
//        return utilisateurDetails;
//    }