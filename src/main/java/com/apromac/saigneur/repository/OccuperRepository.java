package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccuperRepository extends JpaRepository<OccuperEntity, Long> {
    public List<OccuperEntity> findByUtilisateur(UtilisateurEntity utilisateur);
    public OccuperEntity findByUtilisateurAndIsOccuperTrue(UtilisateurEntity utilisateur);
    public OccuperEntity findByPosteAndIsOccuperTrue(PosteEntity posteTDH);
//    public OccuperEntity findByUtilisateurAndIsOccuperTrue(UtilisateurEntity utilisateur);



















//    @Query("SELECT o FROM OccuperEntity o WHERE o.utilisateur.utilisateurID = :utilisateurID AND o.isOccuper = :isActif")
//    public OccuperEntity findByUtilisateurAndOccuperIsTrue(@Param("utilisateurID") Long utilisateurID, @Param("isOccuper") Boolean isActif);

    public List<OccuperEntity> findByPoste(PosteEntity poste);
    public OccuperEntity findByPosteAndIsOccuper(PosteEntity poste, Boolean isActif);
    public OccuperEntity findByUtilisateurAndPoste(UtilisateurEntity utilisateur, PosteEntity poste);
}
