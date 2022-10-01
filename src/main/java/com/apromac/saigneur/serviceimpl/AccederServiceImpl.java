package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.AccederEntity;
import com.apromac.saigneur.entity.MenuEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.repository.AccederRepository;
import com.apromac.saigneur.repository.MenuRepository;
import com.apromac.saigneur.service.AccederService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccederServiceImpl implements AccederService {

    @Autowired
    AccederRepository accederRepository;


    @Autowired
    MenuRepository menuRepository;

    /**
     *
     * @param profilEntity
     * @return
     */
    public List<AccederEntity> findByProfil(ProfilEntity profilEntity) {
        List<AccederEntity> acceders = accederRepository.findByProfil(profilEntity);
        if (acceders.isEmpty())
            throw new NoContentException("Désolé, ce profil ne possede aucun droit sur les menus.");

        return acceders;
    }


    /**
     *
     * @param profilEntity
     * @param menuIDs
     * @return
     */
    public List<AccederEntity> saveMenuByProfil(ProfilEntity profilEntity, List<Long> menuIDs) {
        List<AccederEntity> acces = new ArrayList<>();

        for (Long menuID: menuIDs) {
            Optional<MenuEntity> menuOptional = menuRepository.findById(menuID);
            if (!menuOptional.isPresent())
                continue;

            MenuEntity menuEntity = menuOptional.get();

            AccederEntity accederEntity = new AccederEntity();
            accederEntity.setProfil(profilEntity);
            accederEntity.setMenu(menuEntity);

            AccederEntity saveAcceder = accederRepository.save(accederEntity);
            acces.add(saveAcceder);
        }

        return acces;
    }
}
