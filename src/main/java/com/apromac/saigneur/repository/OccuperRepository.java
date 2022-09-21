package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccuperRepository extends JpaRepository<OccuperEntity, Long> {
    List<OccuperEntity> findByUtilisateur(UtilisateurEntity utilisateur);
    OccuperEntity findByUtilisateurAndIsOccuper(UtilisateurEntity utilisateur, Boolean isActif);
    List<OccuperEntity> findByPoste(PosteEntity poste);
    public OccuperEntity findByPosteAndIsOccuper(PosteEntity poste, Boolean isActif);
}
