package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccuperRepository extends JpaRepository<OccuperEntity, Long> {
//    OccuperEntity findByUtilisateurAndIsOccuper(UtilisateurEntity utilisateur, Boolean IsOccuper);
    OccuperEntity findByUtilisateurAndIsOccuperTrue(UtilisateurEntity utilisateur);
    List<OccuperEntity> findByUtilisateur(UtilisateurEntity utilisateur);
//    OccuperEntity findByPosteAndIsOccuperTrue(PosteEntity poste);
    OccuperEntity findByPosteAndIsOccuper(PosteEntity posteEntity, Boolean isOccuper);
    List<OccuperEntity> findByPoste(PosteEntity poste);
}







//    public List<OccuperEntity> findByPoste(PosteEntity poste);
//    public OccuperEntity findByPosteAndIsOccuperTrue(PosteEntity posteTDH);
//    public List<OccuperEntity> findByDistrictOccuperAndIsOccuperTrue(String district);
//    public OccuperEntity findByUtilisateurAndPoste(UtilisateurEntity utilisateur, PosteEntity poste);


//    public List<OccuperEntity> findByPosteAndDistrictOccuperAndIsOccuperTrue(PosteEntity posteTDH, String district);
//    public List<OccuperEntity> findByPosteAndDistrictOccuper(PosteEntity posteTDH, String district);

//    @Query("SELECT o FROM OccuperEntity o WHERE o.utilisateur.utilisateurID = :utilisateurID AND o.isOccuper = :isActif")
//    public OccuperEntity findByUtilisateurAndOccuperIsTrue(@Param("utilisateurID") Long utilisateurID, @Param("isOccuper") Boolean isActif);
