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

        OccuperEntity posteUtilisateur = occuperRepository.findByUtilisateurAndIsOccuper(utilisateurAuthentifier, true);
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

            OccuperEntity utilisateurPosteOccuper = occuperRepository.findByUtilisateurAndIsOccuper(utilisateur, true);
            if (utilisateurPosteOccuper == null)
                throw new RuntimeException("Désolé, nous ne parvenons pas à satisfaire votre demande. Reéssayez");

            utilisateurDTO.setUtilisateurID(utilisateurPosteOccuper.getUtilisateur().getUtilisateurID());
            utilisateurDTO.setNomUtilisateur(utilisateurPosteOccuper.getUtilisateur().getNomUtilisateur());
            utilisateurDTO.setPrenomsUtilisateur(utilisateurPosteOccuper.getUtilisateur().getPrenomsUtilisateur());
            utilisateurDTO.setUsername(utilisateurPosteOccuper.getUtilisateur().getUsername());
            utilisateurDTO.setPassword(utilisateurPosteOccuper.getUtilisateur().getPassword());
            utilisateurDTO.setPhotoUtilisateur(utilisateurPosteOccuper.getUtilisateur().getPhotoUtilisateur());
            utilisateurDTO.setPosteActuel(utilisateurPosteOccuper.getPoste().getLibellePoste());
            utilisateurDTO.setProfilActuel(utilisateurPosteOccuper.getPoste().getProfil().getLibelleProfil());

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
