package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.ProfilEntity;

import java.util.List;
import java.util.Optional;

public interface ProfilService {
    public ProfilEntity findByProfilID(Long profilID);
    public ProfilEntity saveProfil(ProfilEntity profil);
    public List<ProfilEntity> findAllProfil();
}
