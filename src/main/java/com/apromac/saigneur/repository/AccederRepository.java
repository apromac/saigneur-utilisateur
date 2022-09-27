package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.AccederEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccederRepository extends JpaRepository<AccederEntity, Long> {
}
