package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.ProfilEntity;

import java.util.List;


public interface AccederService {
    public List<AccederEntity> findByProfil(ProfilEntity profilEntity);
    public List<AccederEntity> saveMenuByProfil(ProfilEntity profilEntity, List<Long> menuIDs);
}
