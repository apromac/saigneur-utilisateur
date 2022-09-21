package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteRepository extends JpaRepository<PosteEntity, Long> {
    public List<PosteEntity> findByProfil(ProfilEntity profil);
}
