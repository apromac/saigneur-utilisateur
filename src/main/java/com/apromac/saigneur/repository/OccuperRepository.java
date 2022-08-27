package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.OccuperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccuperRepository extends JpaRepository<OccuperEntity, Long> {
}
