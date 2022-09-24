package com.apromac.saigneur.dto;

import com.apromac.saigneur.entity.ProfilEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PosteDTO {
    private Long posteID;
    private String libellePoste;
    private String zoneBean;
    private String districtBean;
    private ProfilEntity profil;
}
