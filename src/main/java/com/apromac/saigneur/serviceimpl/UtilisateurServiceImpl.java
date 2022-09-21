package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.exception.NotFoundException;
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
