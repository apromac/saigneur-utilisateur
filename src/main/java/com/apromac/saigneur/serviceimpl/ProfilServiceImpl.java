package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.ProfilEntity;
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
     *
     * @param profilID
     * @return
     */
    @Override
    public Optional<ProfilEntity> findByProfilID(Long profilID) {
        Optional<ProfilEntity> profilOptional = profilRepository.findById(profilID);

        if (!profilOptional.isPresent())
            throw new NotFoundException("Désolé, le profil désignée n'existe pas");

        return profilOptional;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ProfilEntity> findAllProfil() {
        List<ProfilEntity> profils = profilRepository.findAll();

        if (profils.isEmpty())
            throw new NotFoundException("Désolé, aucun profil disponible");

        return profils;
    }
    
}
