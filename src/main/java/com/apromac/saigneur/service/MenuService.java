package com.apromac.saigneur.service;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.MenuEntity;

import java.util.List;

public interface MenuService {

    /**
     * Methode permettant de récupérer la liste de menu
     * @return
     */
    public List<MenuEntity> findAllMenu();

    /**
     * Methode permettant de récupérer la liste de menu en fonction de la liste des profils contenus dans les objets
     * accederList
     * @param accederList
     * @return
     */
    public List<MenuEntity> findByAccesProfil(List<AccederEntity> accederList);

}
