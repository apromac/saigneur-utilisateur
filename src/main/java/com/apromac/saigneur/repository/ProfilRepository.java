package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.ProfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<ProfilEntity, Long> {
}
