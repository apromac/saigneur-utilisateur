package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.ProfilEntity;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.proxy.MicroserviceUtilitaireProxy;
import com.apromac.saigneur.repository.PosteRepository;
import com.apromac.saigneur.repository.ProfilRepository;
import com.apromac.saigneur.service.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteServiceImpl implements PosteService {

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private MicroserviceUtilitaireProxy microserviceUtilitaireProxy;




    /**
     * Methode permettant de récupérer la listes des postes
     * @return postes
     */
    @Override
    public List<PosteEntity> findAllPoste() {
        List<PosteEntity> postes = posteRepository.findAll();
        if (postes.isEmpty())
            throw new NoContentException("Désolé, aucun poste disponible");

        return postes;
    }



    /**
     * Methode permettant de récupérer un poste grace à son ID.
     * @param posteID represente l'ID du poste. Il est de type Long
     * @return poste
     */
    @Override
    public PosteEntity findByPosteID(Long posteID) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, le poste désigné n'existe pas");

        return posteOptional.get();
    }



    /**
     * Methode permettant de sauvegarder un poste. Prend en paramètre un objet posteRequest provenant de la partie cliente.
     * @param posteEntity est un objet contenant un ID de profil et un libelle de poste
     * @return posteSave
     */
    @Override
    public PosteEntity savePoste(PosteEntity posteEntity) {
        if (posteEntity.getProfil() == null)
            throw new NotFoundException("Désolé, nous n'avons pas pu récupérer le profil.");

        posteEntity.setLibellePoste(posteEntity.getLibellePoste().toUpperCase().trim());
        PosteEntity posteSave = posteRepository.save(posteEntity);
        if (posteSave == null)
            throw new RuntimeException("Une erreur est survenu lors de la sauvegarde du poste.");

        return posteSave;
    }



    /**
     * Methode permettant de modifier un poste grace à posteTrouver et posteEntity.
     * @param posteTrouver est un objet Poste provenant de la base de données
     * @param posteEntity est un objet Poste provenant de la partie cliente
     * @return posteUpdate
     */
    @Override
    public PosteEntity updatePoste(PosteEntity posteTrouver, PosteEntity posteEntity) {
        if (posteEntity.getProfil() != null) {
            Optional<ProfilEntity> profilOptional = profilRepository.findById(posteEntity.getProfil().getProfilID());
            if (!profilOptional.isPresent())
                throw new NotFoundException("Désolé, le profil désigné n'existe pas.");

            ProfilEntity profil = profilOptional.get();

            posteTrouver.setLibellePoste(posteEntity.getLibellePoste().toUpperCase().trim());
            posteTrouver.setZoneBean(posteEntity.getZoneBean() == null? "" : posteEntity.getZoneBean());
            posteTrouver.setDistrictBean(posteEntity.getDistrictBean() == null? "" : posteEntity.getDistrictBean());
            posteTrouver.setProfil(profil);
        }

        PosteEntity posteUpdate = posteRepository.saveAndFlush(posteTrouver);

        return posteUpdate;
    }



    /**
     * Methode permettant de récupérer la liste des postes grace à l'ID du profil.
     * @param profilID represente l'ID du profil, de type Long
     * @return
     */
    @Override
    public List<PosteEntity> findByProfil(Long profilID) {
        Optional<ProfilEntity> profilOptional = profilRepository.findById(profilID);
        if (!profilOptional.isPresent())
            throw new NotFoundException("Désolé, l'ID de ce profil n'existe pas");

        List<PosteEntity> postes = posteRepository.findByProfil(profilOptional.get());
        if (postes.isEmpty())
            throw new NoContentException("Désolé, aucun poste disponible pour ce profil");

        return postes;
    }



    /**
     * Methode permettant de récupérer la liste des postes en fonction de l'ID du profil et du district.
     * @param profilID represente l'ID du profil
     * @param districtBean represente le libelle du district
     * @return
     */
    @Override
    public List<PosteEntity> findByProfilAndDistrictBean(Long profilID, String districtBean) {
        Optional<ProfilEntity> profilOptional = profilRepository.findById(profilID);
        if (!profilOptional.isPresent())
            throw new NotFoundException("Désolé, l'ID de ce profil n'existe pas");

        List<PosteEntity> postes = posteRepository.findByProfilAndDistrictBean(profilOptional.get(), districtBean);
        if (postes.isEmpty())
            throw new NoContentException("Désolé, aucun poste disponible");

        return postes;
    }

}


//    /**
//     *
//     * @param posteID
//     * @return
//     */
//    public PosteDTO findByPosteDTO(Long posteID) {
//        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
//        if (!posteOptional.isPresent())
//            throw new NotFoundException("Désolé, le poste désignée n'existe pas");
//
//        PosteDTO posteDTO = new PosteDTO();
//        posteDTO.setPosteID(posteOptional.get().getPosteID());
//        posteDTO.setLibellePoste(posteOptional.get().getLibellePoste());
//        posteDTO.setProfil(posteOptional.get().getProfil());
//
//        switch (posteOptional.get().getProfil().getLibelleProfil()) {
//            case "ADMIN":
//                posteDTO.setZoneBean("");
//                posteDTO.setDistrictBean("");
//                break;
//
//            case "TDH":
//                String[] libellePosteTDH = posteOptional.get().getLibellePoste().split(" ");
//
//                ZoneBean zoneBeanResponseEntity = microserviceUtilitaireProxy.recupererUneZone(libellePosteTDH[1].trim());
//                if (zoneBeanResponseEntity == null)
//                    throw new NotFoundException("Désolé, une erreur est survenue lors de la récupération de votre zone.");
//
//                posteDTO.setZoneBean(zoneBeanResponseEntity.getLibelleZone());
//                posteDTO.setDistrictBean(zoneBeanResponseEntity.getDistrict().getLibelleDistrict());
//                break;
//
//            default:
//                String[] libellePoste = posteOptional.get().getLibellePoste().split(" ");
//
//                posteDTO.setZoneBean("");
//                posteDTO.setDistrictBean(libellePoste[1].trim());
//                break;
//        }
//
//        return posteDTO;
//    }
//

//    public PosteEntity savePoste(PosteEntity posteEntity) {
//        Optional<ProfilEntity> profilOptional = profilRepository.findById(posteRequest.getProfilID());
//        if (!profilOptional.isPresent())
//            throw new NotFoundException("Désolé, l'ID ne correspond à aucun profil.");
//
//        PosteEntity posteEntity = new PosteEntity();
//        posteEntity.setLibellePoste(posteRequest.getLibellePoste());
//        posteEntity.setProfil(profilOptional.get());
//
//        PosteEntity posteSave = posteRepository.save(posteEntity);
//        if (posteSave == null)
//            throw new RuntimeException("Une erreur est survenu lors de la sauvegarde du poste.");
//
//        return posteSave;
//    }
