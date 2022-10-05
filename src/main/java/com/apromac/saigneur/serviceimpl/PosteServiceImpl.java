package com.apromac.saigneur.serviceimpl;

import com.apromac.saigneur.bean.ZoneBean;
import com.apromac.saigneur.dto.PosteDTO;
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
     *
     * @param posteID
     * @return
     */
    public PosteDTO findByPosteDTO(Long posteID) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, le poste désignée n'existe pas");

        PosteDTO posteDTO = new PosteDTO();
        posteDTO.setPosteID(posteOptional.get().getPosteID());
        posteDTO.setLibellePoste(posteOptional.get().getLibellePoste());
        posteDTO.setProfil(posteOptional.get().getProfil());

        switch (posteOptional.get().getProfil().getLibelleProfil()) {
            case "ADMIN":
                posteDTO.setZoneBean("");
                posteDTO.setDistrictBean("");
                break;

            case "TDH":
                String[] libellePosteTDH = posteOptional.get().getLibellePoste().split(" ");

                ZoneBean zoneBeanResponseEntity = microserviceUtilitaireProxy.recupererUneZone(libellePosteTDH[1].trim());
                if (zoneBeanResponseEntity == null)
                    throw new NotFoundException("Désolé, une erreur est survenue lors de la récupération de votre zone.");

                posteDTO.setZoneBean(zoneBeanResponseEntity.getLibelleZone());
                posteDTO.setDistrictBean(zoneBeanResponseEntity.getDistrict().getLibelleDistrict());
                break;

            default:
                String[] libellePoste = posteOptional.get().getLibellePoste().split(" ");

                posteDTO.setZoneBean("");
                posteDTO.setDistrictBean(libellePoste[1].trim());
                break;
        }

        return posteDTO;
    }




    /**
     *
     * @param profilID
     * @return
     */
    @Override
    public List<PosteEntity> findByProfil(Long profilID) {
        Optional<ProfilEntity> profilOptional = profilRepository.findById(profilID);
        if (!profilOptional.isPresent())
            throw new NotFoundException("Désolé, ce profil n'existe pas");

        List<PosteEntity> postes = posteRepository.findByProfil(profilOptional.get());
        if (postes.isEmpty())
            throw new NoContentException("Désolé, aucun poste disponible");

        return postes;
    }




    /**
     *
     * @param posteTrouver
     * @param posteEntity
     * @return
     */
    public PosteEntity updatePoste(PosteEntity posteTrouver, PosteEntity posteEntity) {

        if (posteEntity.getProfil() != null) {
            Optional<ProfilEntity> profilOptional = profilRepository.findById(posteEntity.getProfil().getProfilID());
            if (!profilOptional.isPresent())
                throw new NotFoundException("");

            ProfilEntity profil = profilOptional.get();

            posteTrouver.setLibellePoste(posteEntity.getLibellePoste());
            posteTrouver.setProfil(profil);
        }

        PosteEntity posteUpdate = posteRepository.saveAndFlush(posteTrouver);

        return posteUpdate;
    }




























    /**
     *
     * @param posteID
     * @return
     */
    @Override
    public PosteEntity findByPosteID(Long posteID) {
        Optional<PosteEntity> posteOptional = posteRepository.findById(posteID);
        if (!posteOptional.isPresent())
            throw new NotFoundException("Désolé, le poste désignée n'existe pas");

        return posteOptional.get();
    }



    /**
     *
     * @param poste
     * @return
     */
    public PosteEntity savePoste(PosteEntity poste) {
        PosteEntity posteSave = posteRepository.save(poste);
        if (posteSave == null)
            throw new RuntimeException("Une erreur est survenu lors de la sauvegarde du poste.");

        return posteSave;
    }

    /**
     *
     * @return
     */
    @Override
    public List<PosteEntity> findAllPoste() {
        List<PosteEntity> postes = posteRepository.findAll();
        if (postes.isEmpty())
            throw new NotFoundException("Désolé, aucun poste disponible");

        return postes;
    }

}
