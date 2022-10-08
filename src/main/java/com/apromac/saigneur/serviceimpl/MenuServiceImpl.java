package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.MenuEntity;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.repository.MenuRepository;
import com.apromac.saigneur.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;


    /**
     *
     * @param accederList
     * @return
     */
    @Override
    public List<MenuEntity> findByProfil(List<AccederEntity> accederList) {
        List<MenuEntity> menus = new ArrayList<>();

        for (AccederEntity acces : accederList) {
            MenuEntity menu = acces.getMenu();
            menus.add(menu);
        }

        return menus;
    }


    /**
     *
     * @return
     */
    @Override
    public List<MenuEntity> findAllMenu() {
        List<MenuEntity> menus = menuRepository.findAll();
        if (menus.isEmpty())
            throw new NoContentException("Désolé, aucun menu disponible.");

        return menus;
    }
}
