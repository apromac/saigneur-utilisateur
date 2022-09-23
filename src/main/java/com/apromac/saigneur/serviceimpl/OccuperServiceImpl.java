package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.bean.DistrictBean;
import com.apromac.saigneur.bean.ZoneBean;
import com.apromac.saigneur.entity.OccuperEntity;
import com.apromac.saigneur.entity.PosteEntity;
import com.apromac.saigneur.entity.UtilisateurEntity;
import com.apromac.saigneur.exception.NoContentException;
import com.apromac.saigneur.exception.NotFoundException;
import com.apromac.saigneur.proxy.MicroserviceUtilitaireProxy;
import com.apromac.saigneur.repository.OccuperRepository;
import com.apromac.saigneur.repository.PosteRepository;
import com.apromac.saigneur.repository.UtilisateurRepository;
import com.apromac.saigneur.service.OccuperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccuperServiceImpl implements OccuperService {

    @Autowired
    private OccuperRepository occuperRepository;

    @Autowired
    private PosteRepository posteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private MicroserviceUtilitaireProxy microserviceUtilitaireProxy;


    /**
     *
     * @param utilisateurID
     * @return
     */
    @Override
    public List<OccuperEntity> findByUtilisateur(Long utilisateurID) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);
        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, ce utilisateur n'existe pas");

        List<OccuperEntity> occupent = occuperRepository.findByUtilisateur(utilisateurOptional.get());
        if (occupent.isEmpty())
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occupent;
    }

    /**
     *
     * @param utilisateurID
     * @param isActif
     * @return
     */
    @Override
    public OccuperEntity findByUtilisateurAndIsOccuper(Long utilisateurID, Boolean isActif) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);
        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, ce utilisateur n'existe pas");

        OccuperEntity occuper = occuperRepository.findByUtilisateurAndIsOccuperTrue(utilisateurOptional.get());
        if (occuper == null)
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occuper;
    }

    /**
     *
     * @param posteID
     * @return
     */
    @Override
    public List<OccuperEntity> findByPoste(Long posteID) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, ce poste n'existe pas");

        List<OccuperEntity> occupent = occuperRepository.findByPoste(posteOptional.get());
        if (occupent.isEmpty())
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occupent;
    }

    public OccuperEntity findByPosteAndIsOccuper(Long posteID, Boolean isActif) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, ce poste n'existe pas");

        OccuperEntity occuper = occuperRepository.findByPosteAndIsOccuper(posteOptional.get(), isActif);
        if (occuper == null)
            throw new NoContentException("Désolé, aucun poste occupé disponible");

        return occuper;
    }

//    /**
//     *
//     * @param utilisateur
//     * @param poste
//     * @return
//     */
//    public OccuperEntity saveOccuper(UtilisateurEntity utilisateur, PosteEntity poste) {
//        OccuperEntity occuperEntity = occuperRepository.findByUtilisateurAndPoste(utilisateur, poste);
//        if (occuperEntity != null)
//            throw new RuntimeException("Désolé, Cet utilisateur a deja occupé se poste");
//
//        OccuperEntity occuper = new OccuperEntity();
//        occuper.setUtilisateur(utilisateur);
//        occuper.setPoste(poste);
//        occuper.setLibelleOccuper("");
//        occuper.setDateOccuper(null);
//        occuper.setIsOccuper(true);
//
//        OccuperEntity occuperSave = occuperRepository.save(occuper);
//        if (occuperSave == null)
//            throw new RuntimeException("Désolé, nous avons rencontré un problème lors de la sauvegarde");
//
//        return occuperSave;
//    }

    /**
     *
     * @param utilisateurID
     * @param posteID
     * @return
     */
    public OccuperEntity saveOccuper(Long utilisateurID, Long posteID) {
        Optional<UtilisateurEntity> utilisateurOptional = utilisateurRepository.findById(utilisateurID);
        if (!utilisateurOptional.isPresent())
            throw new NotFoundException("Désolé, aucun utilisateur trouvé à partir de l'ID");

        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, aucun poste trouvé à partie de l'ID");

        OccuperEntity occuperEntity = buildZoneUtilisateur(utilisateurOptional.get(), posteOptional.get());
        OccuperEntity saveOccuper = occuperRepository.save(occuperEntity);

        return saveOccuper;
    }

    /**
     *
     * @param utilisateurEntity
     * @param posteEntity
     * @return
     */
    public OccuperEntity buildZoneUtilisateur(UtilisateurEntity utilisateurEntity, PosteEntity posteEntity) {

        OccuperEntity occuperEntity = new OccuperEntity();
        occuperEntity.setUtilisateur(utilisateurEntity);
        occuperEntity.setPoste(posteEntity);
        occuperEntity.setLibelleOccuper("a");
        occuperEntity.setDateOccuper(null);
        occuperEntity.setIsOccuper(true);

        switch (posteEntity.getProfil().getLibelleProfil()) {
            case "ADMIN":
                break;

            case "COORDINATEUR":
                break;

            case "SECRETAIRE":
                break;

            case "TDH":
                ZoneBean zoneBeanResponseEntity = microserviceUtilitaireProxy.recupererUneZone(posteEntity.getPosteID());
                if (zoneBeanResponseEntity == null)
                    throw new RuntimeException("Désolé, une erreur est survenue lors de la récupération de votre zone.");

                occuperEntity.setZoneOccuper(zoneBeanResponseEntity.getLibelleZone());
                occuperEntity.setDistrictOccuper(zoneBeanResponseEntity.getDistrict().getLibelleDistrict());
                break;

            default:
        }

        return occuperEntity;
    }
}
