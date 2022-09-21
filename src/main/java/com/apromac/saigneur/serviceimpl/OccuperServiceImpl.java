package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.repository.OccuperRepository;
import com.apromac.saigneur.repository.PosteRepository;
import com.apromac.saigneur.repository.UtilisateurRepository;
import com.apromac.saigneur.service.OccuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccuperServiceImpl implements OccuperService {

    @Autowired
    private OccuperRepository occuperRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    /**
     *
     * @param utilisateurID
     * @return
     */
    @Override
    public List<OccuperEntity> findByUtilisateur(Long utilisateurID) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);
        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, ce utilisateur n'existe pas");

        List<OccuperEntity> occupent = occuperRepository.findByUtilisateur(utilisateurOptional.get());
        if (occupent.isEmpty())
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occupent;
    }

    /**
     *
     * @param utilisateurID
     * @param isActif
     * @return
     */
    @Override
    public OccuperEntity findByUtilisateurAndIsOccuper(Long utilisateurID, Boolean isActif) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);
        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, ce utilisateur n'existe pas");

        OccuperEntity occuper = occuperRepository.findByUtilisateurAndIsOccuper(utilisateurOptional.get(), isActif);
        if (occuper == null)
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occuper;
    }

    /**
     *
     * @param posteID
     * @return
     */
    @Override
    public List<OccuperEntity> findByPoste(Long posteID) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, ce poste n'existe pas");

        List<OccuperEntity> occupent = occuperRepository.findByPoste(posteOptional.get());
        if (occupent.isEmpty())
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occupent;
    }

    public OccuperEntity findByPosteAndIsOccuper(Long posteID, Boolean isActif) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, ce poste n'existe pas");

        OccuperEntity occuper = occuperRepository.findByPosteAndIsOccuper(posteOptional.get(), isActif);
        if (occuper == null)
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occuper;
    }
}
