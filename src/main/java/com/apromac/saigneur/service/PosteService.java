package com.apromac.saigneur.service;

import com.apromac.saigneur.dto.PosteDTO;
import com.apromac.saigneur.entity.PosteEntity;

import java.util.List;

public interface PosteService {
    public PosteDTO findByPosteDTO(Long posteID);
    public List<PosteEntity> findByProfil(Long profilID);









    public PosteEntity findByPosteID(Long posteID);
    public PosteEntity savePoste(PosteEntity poste);
    public List<PosteEntity> findAllPoste();

}
