package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.dto.UtilisateurAuthDTO;
import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.repository.OccuperRepository;
import com.apromac.saigneur.repository.UtilisateurRepository;
import com.apromac.saigneur.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new NotFoundException("Désolé, le utilisateur désignée n'existe pas");

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
    public UtilisateurAuthDTO authentification(String username, String password) {
        UtilisateurEntity utilisateurAuthentifier = utilisateurRepository.findByUsernameAndPassword(username, password);
        if (utilisateurAuthentifier == null)
            throw new RuntimeException("Une erreur est survenu lors de l'authentification de l'utilisateur.");

        OccuperEntity posteUtilisateur = occuperRepository.findByUtilisateurAndIsOccuper(utilisateurAuthentifier, true);
        if (posteUtilisateur == null)
            throw new RuntimeException("Désolé, nous avons rencontré un problème lors de la synchronisation des données");

        UtilisateurAuthDTO utilisateurAuthDTO = new UtilisateurAuthDTO();
        utilisateurAuthDTO.setUtilisateurID(posteUtilisateur.getUtilisateur().getUtilisateurID());
        utilisateurAuthDTO.setNomUtilisateur(posteUtilisateur.getUtilisateur().getNomUtilisateur());
        utilisateurAuthDTO.setPrenomsUtilisateur(posteUtilisateur.getUtilisateur().getPrenomsUtilisateur());
        utilisateurAuthDTO.setUsername(posteUtilisateur.getUtilisateur().getUsername());
        utilisateurAuthDTO.setPassword(posteUtilisateur.getUtilisateur().getPassword());
        utilisateurAuthDTO.setPhotoUtilisateur(posteUtilisateur.getUtilisateur().getPhotoUtilisateur());
        utilisateurAuthDTO.setPosteActuel(posteUtilisateur.getPoste().getLibellePoste());
        utilisateurAuthDTO.setProfilActuel(posteUtilisateur.getPoste().getProfil().getLibelleProfil());

        return utilisateurAuthDTO;
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
