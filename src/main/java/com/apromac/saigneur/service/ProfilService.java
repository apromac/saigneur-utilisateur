package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.ProfilEntity;

import java.util.List;

public interface ProfilService {
    public ProfilEntity findByProfilID(Long profilID);
    public ProfilEntity saveProfil(ProfilEntity profil);
    public List<ProfilEntity> findAllProfil();
    public ProfilEntity updateProfil(ProfilEntity profilTrouver, ProfilEntity profilEntity);
}
