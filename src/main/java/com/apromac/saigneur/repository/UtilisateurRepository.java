package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Long> {
    public List<UtilisateurEntity> findByProfil(Long utilisateurID);
}
