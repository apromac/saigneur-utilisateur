package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Long> {
    public UtilisateurEntity findByUsernameAndPassword(String username, String password);
}
