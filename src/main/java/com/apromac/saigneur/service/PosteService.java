package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.PosteEntity;

import java.util.List;
import java.util.Optional;

public interface PosteService {
    public Optional<PosteEntity> findByPosteID(Long posteID);
    public List<PosteEntity> findAllPoste();
}
