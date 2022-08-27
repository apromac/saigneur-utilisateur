package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.ProfilEntity;

import java.util.List;
import java.util.Optional;

public interface ProfilService {
    public Optional<ProfilEntity> findByProfilID(Long profilID);
    public List<ProfilEntity> findAllProfil();
}
