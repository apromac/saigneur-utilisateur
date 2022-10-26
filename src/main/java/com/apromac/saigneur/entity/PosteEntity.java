package com.apromac.saigneur.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "saigneurutilisateur", name = "poste")
public class PosteEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poste", updatable = false, nullable = false)
    private Long posteID;

    @Column(name = "lib_poste", nullable = false)
    private String libellePoste;

    @Column(name = "zone_poste", nullable = true)
    private String zoneBean;

    @Column(name = "district_poste", nullable = true)
    private String districtBean;

    @ManyToOne
    @JoinColumn(name = "code_profil", nullable = false)
    private ProfilEntity profil;

}
