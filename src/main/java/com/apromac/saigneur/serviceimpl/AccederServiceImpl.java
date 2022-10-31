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
     * Méthode permettant de sauvegarder les droits d'accès menu d'un profil en fonction d'un objet Profil et d'une liste
     * d'ID de menu.
     * @param profilEntity represente l'objet Profil
     * @param menuIDs represente la liste des ID menu
     * @return acces
     */
    public List<AccederEntity> saveMenuByProfil(ProfilEntity profilEntity, List<Long> menuIDs) {
        deleteAccesMenu(profilEntity);

        List<AccederEntity> accesMenu = addAccesMenu(profilEntity, menuIDs);

        return accesMenu;
    }


    /**
     *
     * @param profilEntity
     */
    private void deleteAccesMenu(ProfilEntity profilEntity) {
        List<AccederEntity> acceder = accederRepository.findByProfil(profilEntity);
        accederRepository.deleteAll(acceder);
    }


    /**
     *
     * @param profilEntity
     * @param menuIDs
     * @return
     */
    private List<AccederEntity> addAccesMenu(ProfilEntity profilEntity, List<Long> menuIDs) {
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


    /**
     * Methode permettant de récupérer la liste des accès menu d'un profil.
     * @param profilEntity represente l'objet profil identifié
     * @return acceder
     */
    public List<AccederEntity> findByProfil(ProfilEntity profilEntity) {
        List<AccederEntity> acceders = accederRepository.findByProfil(profilEntity);
        if (acceders.isEmpty())
            throw new NoContentException("Désolé, ce profil ne possede aucun droit sur les menus.");

        return acceders;
    }

}
