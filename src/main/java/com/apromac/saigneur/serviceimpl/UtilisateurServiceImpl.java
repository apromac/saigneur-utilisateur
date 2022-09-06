package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.repository.ProfilRepository;
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
    private ProfilRepository profilRepository;

    /**
     *
     * @param utilisateurID
     * @return
     */
    @Override
    public Optional<UtilisateurEntity> findByUtilisateurID(Long utilisateurID) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);

        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, le utilisateur désignée n'existe pas");

        return utilisateurOptional;
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

    /**
     *
     * @param profilID
     * @return
     */
    @Override
    public List<UtilisateurEntity> findByProfil(Long profilID) {
        List<UtilisateurEntity> utilisateurs = utilisateurRepository.findByProfil(profilID);

        if (utilisateurs.isEmpty())
            throw new NotFoundException("Désolé, aucun utilisateur disponible");

        return utilisateurs;
    }
}
