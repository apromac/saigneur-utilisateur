package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.MenuEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccederRepository extends JpaRepository<AccederEntity, Long> {

    List<AccederEntity> findByProfil(ProfilEntity profilEntity);

    AccederEntity findByProfilAndMenu(ProfilEntity profilEntity, MenuEntity menuEntity);
}
