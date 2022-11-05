package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.repository.ProfilRepository;
import com.apromac.saigneur.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilServiceImpl implements ProfilService {

    @Autowired
    private ProfilRepository profilRepository;



    /**
     * Méthode permettant de récupérer la liste des profils
     * @return profils
     */
    @Override
    public List<ProfilEntity> findAllProfil() {
        List<ProfilEntity> profils = profilRepository.findAll();
        if (profils.isEmpty())
            throw new NoContentException("Désolé, aucun profil disponible");

        return profils;
    }



    /**
     * Methode permettant de récupérer un profil grace à son ID. Elle prend en paramèttre l'ID du profil.
     * @param profilID
     * @return
     */
    @Override
    public ProfilEntity findByProfilID(Long profilID) {
        Optional<ProfilEntity> profilOptional = profilRepository.findById(profilID);
        if (!profilOptional.isPresent())
            throw new NotFoundException("Désolé, le profil désigné n'existe pas");

        return profilOptional.get();
    }



    /**
     * Methode permettant de modifier un profil. Elle prend en premier paramètre le profil existant dans la base de
     * données puis, en second paramètre le profil provenant de la partie cliente.
     * @param profilTrouver
     * @param profilEntity
     * @return profilUpdate
     */
    public ProfilEntity updateProfil(ProfilEntity profilTrouver, ProfilEntity profilEntity) {
        profilTrouver.setLibelleProfil(profilEntity.getLibelleProfil());

        ProfilEntity profilUpdate = profilRepository.saveAndFlush(profilTrouver);

        return profilUpdate;
    }



    /**
     * Methode permettant de sauvegarder un profil. Prend en argument le profil à sauvegarder
     * @param profil
     * @return profilSave
     */
    public ProfilEntity saveProfil(ProfilEntity profil) {
        profil.getLibelleProfil().toUpperCase();
        ProfilEntity profilSave = profilRepository.save(profil);
        if (profilSave == null)
            throw new RuntimeException("Une erreur est survenu lors de la sauvegarde du profil.");

        return profilSave;
    }

}
