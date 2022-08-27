package com.apromac.saigneur.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "saigneurutilisateur", name = "profil")
public class ProfilEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profil", updatable = false, nullable = false)
    private Long campagneID;

    @Column(name = "lib_profil", nullable = false)
    private String libelleProfil;

}
