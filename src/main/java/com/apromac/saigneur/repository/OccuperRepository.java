package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccuperRepository extends JpaRepository<OccuperEntity, Long> {
    public List<OccuperEntity> findByUtilisateur(UtilisateurEntity utilisateur);

    @Query("SELECT o FROM OccuperEntity o WHERE o.utilisateur.utilisateurID = :utilisateurID AND o.isOccuper = :isActif")
    public OccuperEntity findByUtilisateurAndOccuperIsTrue(Long utilisateurID, Boolean isActif);

    public List<OccuperEntity> findByPoste(PosteEntity poste);
    public OccuperEntity findByPosteAndIsOccuper(PosteEntity poste, Boolean isActif);
    public OccuperEntity findByUtilisateurAndPoste(UtilisateurEntity utilisateur, PosteEntity poste);
}
