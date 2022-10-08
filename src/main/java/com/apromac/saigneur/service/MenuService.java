package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.MenuEntity;

import java.util.List;

public interface MenuService {
    public List<MenuEntity> findByProfil(List<AccederEntity> accederList);
    public List<MenuEntity> findAllMenu();



}
