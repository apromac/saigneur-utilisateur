package com.apromac.saigneur.repository;

import com.apromac.saigneur.entity.PosteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosteRepository extends JpaRepository<PosteEntity, Long> {
}
