package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.repository.PosteRepository;
import com.apromac.saigneur.service.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteServiceImpl implements PosteService {

    @Autowired
    private PosteRepository posteRepository;

    /**
     *
     * @param posteID
     * @return
     */
    @Override
    public PosteEntity findByPosteID(Long posteID) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, le poste désignée n'existe pas");

        return posteOptional.get();
    }

    /**
     *
     * @param poste
     * @return
     */
    public PosteEntity savePoste(PosteEntity poste) {
        PosteEntity posteSave = posteRepository.save(poste);
        if (posteSave == null)
            throw new RuntimeException("Une erreur est survenu lors de la sauvegarde du poste.");

        return posteSave;
    }

    /**
     *
     * @return
     */
    @Override
    public List<PosteEntity> findAllPoste() {
        List<PosteEntity> postes = posteRepository.findAll();
        if (postes.isEmpty())
            throw new NotFoundException("Désolé, aucun poste disponible");

        return postes;
    }

}
